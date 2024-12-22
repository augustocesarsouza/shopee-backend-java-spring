package com.backend.shopee.shopee_backend.data.utilityExternal;

import com.backend.shopee.shopee_backend.data.cloudinaryUtil.CloudinaryCreate;
import com.backend.shopee.shopee_backend.data.cloudinaryUtil.CloudinaryResult;
import com.backend.shopee.shopee_backend.data.utilityExternal.Interface.ICloudinaryUti;
import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.api.ApiResponse;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CloudinaryUti implements ICloudinaryUti {
    private final Cloudinary cloudinary;

    @Autowired
    public CloudinaryUti(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }


    @Override
    public CloudinaryCreate CreateMedia(String url, String folder, Integer width, Integer height) {
        try {
            boolean isImage = url.startsWith("data:image");
            boolean isVideo = url.startsWith("data:video");

            // Configurar transformações explicitamente
            var transformation = new Transformation<>()
                    .width(width)
                    .height(height)
                    .crop("fill")
                    .quality("auto");

            // Parâmetros de upload
            Map<String, Object> uploadParams = ObjectUtils.asMap(
                    "folder", folder,
                    "transformation", transformation
            );

            if (isImage) {
                uploadParams.put("format", "jpg");
            } else if (isVideo) {
                uploadParams.put("resource_type", "video");
            } else {
                throw new RuntimeException("Unsupported media type");
            }

            // Fazer o upload
            var uploadResult = cloudinary.uploader().upload(url, uploadParams);

            // Criar objeto de retorno
            CloudinaryCreate cloudinaryCreate = new CloudinaryCreate();
            cloudinaryCreate.setImgUrl((String) uploadResult.get("secure_url"));
            cloudinaryCreate.setPublicId((String) uploadResult.get("public_id"));
            cloudinaryCreate.setCreatedSuccessfully(true);

            return cloudinaryCreate;

        } catch (Exception ex) {
            throw new RuntimeException("Error while uploading media: " + ex.getMessage(), ex);
        }
    }

    @Override
    public CloudinaryResult DeleteFileCloudinaryExtractingPublicIdFromUrlList(String url) {
        CloudinaryResult cloudinaryResult = new CloudinaryResult();

        try {
            // Regex para capturar o resourceType, folder e publicId
            Pattern pattern = Pattern.compile("/(image|video)/upload/(?:v\\d+/)?([^/]+)/([^/.]+)");
            Matcher matcher = pattern.matcher(url);

            if(!matcher.find()){
                cloudinaryResult.setDeleteSuccessfully(false);
                cloudinaryResult.setMessage("Failed to extract public ID from URL.");
                return cloudinaryResult;
            }

            String resourceType = matcher.group(1); // Tipo de recurso: "image" ou "video"
            String folder = matcher.group(2);       // Pasta: "img-user"
            String publicId = matcher.group(3);     // PublicId: "jxpl6lrhuisuoqclx6bo"

            String publicIdAll = folder + "/" + publicId;

            List<String> publicList = new ArrayList<>();
            publicList.add(publicIdAll);

            return DeleteMediaCloudinary(publicList, resourceType);

        } catch (Exception e) {
            cloudinaryResult.setDeleteSuccessfully(false);
            cloudinaryResult.setMessage(e.getMessage());
            return cloudinaryResult;
        }
    }

    @Override
    public CloudinaryResult DeleteMediaCloudinary(List<String> publicList, String resourceType) {
        CloudinaryResult cloudinaryResult = new CloudinaryResult();

        try {
            var deleteParams = ObjectUtils.asMap(
                    "resource_type", resourceType // Define o tipo de recurso (image ou video)
            );

            ApiResponse delete = cloudinary.api().deleteResources(publicList, deleteParams);

            var result = delete.get("deleted");

            if(result == null){
                cloudinaryResult.setDeleteSuccessfully(false);
                cloudinaryResult.setMessage("Unable to delete image in cloudinary");
            }

//            var result = cloudinary.uploader().destroy(publicId, deleteParams);
            cloudinaryResult.setDeleteSuccessfully(true);
        } catch (Exception e) {
            cloudinaryResult.setDeleteSuccessfully(false);
            cloudinaryResult.setMessage(e.getMessage());
        }
        return cloudinaryResult;
    }

    @Override
    public CloudinaryResult DeleteFileCloudinaryExtractingPublicIdFromUrl(String url, String resourceType) {
        CloudinaryResult cloudinaryResult = new CloudinaryResult();

        try {
            Pattern pattern = Pattern.compile("upload/(?:v\\d+/)?.*/([^/.]+)");
            Matcher matcher = pattern.matcher(url);

            if(!matcher.find()){
                cloudinaryResult.setDeleteSuccessfully(false);
                cloudinaryResult.setMessage("Failed to extract public ID from URL.");
                return cloudinaryResult;
            }

            String publicId = matcher.group(1);

            return DeleteMediaCloudinary(new ArrayList<>(), "");

        } catch (Exception e) {
            cloudinaryResult.setDeleteSuccessfully(false);
            cloudinaryResult.setMessage(e.getMessage());
            return cloudinaryResult;
        }
    }
}

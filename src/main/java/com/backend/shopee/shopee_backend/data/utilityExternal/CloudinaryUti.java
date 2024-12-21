package com.backend.shopee.shopee_backend.data.utilityExternal;

import com.backend.shopee.shopee_backend.application.services.ResultService;
import com.backend.shopee.shopee_backend.data.cloudinaryUtil.CloudinaryCreate;
import com.backend.shopee.shopee_backend.data.cloudinaryUtil.CloudinaryResult;
import com.backend.shopee.shopee_backend.data.utilityExternal.Interface.ICloudinaryUti;
import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

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

            return cloudinaryCreate;

        } catch (Exception ex) {
            throw new RuntimeException("Error while uploading media: " + ex.getMessage(), ex);
        }
    }

//    @Override
//    public ResultService<CloudinaryCreate> CreateImg(String url, Integer width, Integer height) {
//        try {
//            Map uploadResult = cloudinary.uploader().upload(url, ObjectUtils.asMap(
//                    "transformation", new Transformation<>().width(width).height(height).crop("fill").quality(100)
//            ));
//
//            String publicId = (String) uploadResult.get("public_id");
//            String imageUrl = (String) uploadResult.get("url");
//
//            return ResultService.Ok(new CloudinaryCreate(publicId, imageUrl));
//        }catch (Exception e){
//            return ResultService.Fail(e.getMessage());
//        }
//    }

    @Override
    public CloudinaryResult DeleteMediaCloudinary(String publicId, String resourceType) {
        CloudinaryResult cloudinaryResult = new CloudinaryResult();
        try {
            Map<String, Object> deleteParams = ObjectUtils.asMap(
                    "resource_type", resourceType
            );

            Map result = cloudinary.uploader().destroy(publicId, deleteParams);

            cloudinaryResult.setDeleteSuccessfully("ok".equals(result.get("result")));
        } catch (Exception e) {
            cloudinaryResult.setDeleteSuccessfully(false);
            cloudinaryResult.setMessage(e.getMessage());
        }
        return cloudinaryResult;
    }
}

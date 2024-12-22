package com.backend.shopee.shopee_backend.data.utilityExternal.Interface;

import com.backend.shopee.shopee_backend.data.cloudinaryUtil.CloudinaryCreate;
import com.backend.shopee.shopee_backend.data.cloudinaryUtil.CloudinaryResult;

import java.util.List;

public interface ICloudinaryUti {
    CloudinaryCreate CreateMedia(String url, String folder, Integer width, Integer height);
    CloudinaryResult DeleteFileCloudinaryExtractingPublicIdFromUrlList(String url);
    CloudinaryResult DeleteMediaCloudinary(List<String> publicList, String resourceType);
    CloudinaryResult DeleteFileCloudinaryExtractingPublicIdFromUrl(String url, String resourceType);
}
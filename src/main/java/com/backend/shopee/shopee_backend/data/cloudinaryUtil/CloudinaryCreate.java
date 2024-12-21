package com.backend.shopee.shopee_backend.data.cloudinaryUtil;

public class CloudinaryCreate {
    private String PublicId;
    private String ImgUrl;

    public CloudinaryCreate(String publicId, String imgUrl) {
        PublicId = publicId;
        ImgUrl = imgUrl;
    }

    public CloudinaryCreate() {
    }

    public String getPublicId() {
        return PublicId;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setPublicId(String publicId) {
        PublicId = publicId;
    }

    public void setImgUrl(String imgUrl) {
        ImgUrl = imgUrl;
    }
}

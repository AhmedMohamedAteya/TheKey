package apps.pixel.the.key.models.home;

public class sliderResponse {
    private String CategoryType;
    private String ImageUrl;
    private String SubCategoryId;
    private String CategoryNameEn;
    private String CategoryNameAr;
    private String CategoryLogo;

    public String getCategoryNameEn() {
        return CategoryNameEn;
    }

    public void setCategoryNameEn(String categoryNameEn) {
        CategoryNameEn = categoryNameEn;
    }

    public String getCategoryNameAr() {
        return CategoryNameAr;
    }

    public void setCategoryNameAr(String categoryNameAr) {
        CategoryNameAr = categoryNameAr;
    }

    public String getCategoryLogo() {
        return CategoryLogo;
    }

    public void setCategoryLogo(String categoryLogo) {
        CategoryLogo = categoryLogo;
    }

    public String getCategoryType() {
        return CategoryType;
    }

    public void setCategoryType(String categoryType) {
        CategoryType = categoryType;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getSubCategoryId() {
        return SubCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        SubCategoryId = subCategoryId;
    }
}

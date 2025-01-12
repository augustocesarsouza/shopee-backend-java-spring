package com.backend.shopee.shopee_backend.domain.enums;

public enum ProductOfferFlashType {
    MOST_POPULAR("Mais_Populares"),
    OFFICIAL_STORES("Lojas_Oficiais"),
    TOP_DEALS("Top_Ofertas"),
    FASHION("Moda"),
    BEAUTY_AND_PERSONAL_CARE("Beleza_e_Cuidado_Pessoal"),
    INTERNATIONAL_OFFERS("Ofertas_Internacionais"),
    KIDS_FASHION("Moda_Infantil"),
    GROCERY_AND_PETS("Mercado_e_Pets"),
    HOME_AND_KITCHEN("Casa_e_Cozinha"),
    TOYS("Brinquedos"),
    ELECTRONICS("Eletrônicos"),
    BABY_CARE("Cuidados_para_o_Bebê"),
    BOOKS_AND_STATIONERY("Livros_e_Papelaria"),
    COMPUTERS_AND_ACCESSORIES("Computadores_e_Acessórios"),
    MOBILE_PHONES_AND_DEVICES("Celulares_e_Dispositivos"),
    AUTO_AND_MOTO("Auto_e_Moto"),
    SPORTS_AND_LEISURE("Esportes_e_Lazer"),
    MORE_LOCAL_DEALS("Mais_Ofertas_Locais");

    private final String description;

    ProductOfferFlashType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static ProductOfferFlashType fromDescription(String description) {
        for (ProductOfferFlashType type : values()) {
            if (type.getDescription().equalsIgnoreCase(description)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown description: " + description);
    }

    public static boolean isValidTagProduct(String tagProduct) {
        try {
            ProductOfferFlashType.valueOf(tagProduct.toUpperCase()); // Converte para maiúsculas e tenta fazer o parse
            return true;
        } catch (IllegalArgumentException e) {
            return false; // Retorna false caso o valor não seja um valor válido do enum
        }
    }
}

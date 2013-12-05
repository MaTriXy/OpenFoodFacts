package com.flavienlaurent.openfoodfacts.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

//FIXME primitive type
//FIXME clean unused
public class Product implements Serializable {

    public String _id;
    public ArrayList<String> _keywords = new ArrayList<String>();
    public Integer additives_n;
    public ArrayList<String> additives_tags = new ArrayList<String>();
    public String brands;
    public ArrayList<String> brands_tags = new ArrayList<String>();
    public String categories;
    public ArrayList<String> categories_hierarchy = new ArrayList<String>();
    public ArrayList<String> categories_tags = new ArrayList<String>();
    public ArrayList<Object> checkers = new ArrayList<Object>();
    public ArrayList<Object> checkers_tags = new ArrayList<Object>();
    public ArrayList<Object> cities_tags = new ArrayList<Object>();
    public String code;
    public Integer complete;
    public Long completed_t;
    public ArrayList<String> correctors = new ArrayList<String>();
    public ArrayList<String> correctors_tags = new ArrayList<String>();
    public Long created_t;
    public String creator;
    public ArrayList<String> editors = new ArrayList<String>();
    public String emb_codes;
    public String emb_codes_orig;
    public ArrayList<String> emb_codes_tags = new ArrayList<String>();
    public String expiration_date;
    public String generic_name;
    public String id;
    public String image_small_url;
    public String image_url;
    public ArrayList<String> informers = new ArrayList<String>();
    public ArrayList<String> informers_tags = new ArrayList<String>();
    public ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
    public Integer ingredients_from_or_that_may_be_from_palm_oil_n;
    public Integer ingredients_from_palm_oil_n;
    public ArrayList<Object> ingredients_from_palm_oil_tags = new ArrayList<Object>();
    public ArrayList<String> ingredients_tags = new ArrayList<String>();
    public String ingredients_text;
    public Integer ingredients_that_may_be_from_palm_oil_n;
    public ArrayList<Object> ingredients_that_may_be_from_palm_oil_tags = new ArrayList<Object>();
    public String interface_version_modified;
    public String labels;
    public ArrayList<Object> labels_hierarchy = new ArrayList<Object>();
    public ArrayList<Object> labels_tags = new ArrayList<Object>();
    public String lang;
    public String last_modified_by;
    public Integer last_modified_t;
    public String lc;
    public String max_imgid;
    public Object no_nutrition_data;
    public Nutrient_levels nutrient_levels;
    public ArrayList<String> nutrient_levels_tags = new ArrayList<String>();
    public Nutriments nutriments;
    public String nutrition_data_per;
    public String origins;
    public ArrayList<String> origins_tags = new ArrayList<String>();
    public String packaging;
    public ArrayList<String> packaging_tags = new ArrayList<String>();
    public ArrayList<String> photographers = new ArrayList<String>();
    public ArrayList<String> photographers_tags = new ArrayList<String>();
    public String product_name;
    public String purchase_places;
    public ArrayList<Object> purchase_places_tags = new ArrayList<Object>();
    public String quantity;
    public Integer rev;
    public String serving_quantity;
    public String serving_size;
    public String status;
    public ArrayList<String> status_tags = new ArrayList<String>();
    public String stores;
    public ArrayList<Object> stores_tags = new ArrayList<Object>();
    public String traces;
    public ArrayList<Object> traces_tags = new ArrayList<Object>();
    public ArrayList<Object> unknown_nutrients_tags = new ArrayList<Object>();
    public HashMap<String, Object> additionalProperties = new HashMap<String, Object>();

    @Override
    public String toString() {
        return "Product{" +
                "_id='" + _id + '\'' +
                ", _keywords=" + _keywords +
                ", additives_n=" + additives_n +
                ", additives_tags=" + additives_tags +
                ", brands='" + brands + '\'' +
                ", brands_tags=" + brands_tags +
                ", categories='" + categories + '\'' +
                ", categories_hierarchy=" + categories_hierarchy +
                ", categories_tags=" + categories_tags +
                ", checkers=" + checkers +
                ", checkers_tags=" + checkers_tags +
                ", cities_tags=" + cities_tags +
                ", code='" + code + '\'' +
                ", complete=" + complete +
                ", completed_t=" + completed_t +
                ", correctors=" + correctors +
                ", correctors_tags=" + correctors_tags +
                ", created_t=" + created_t +
                ", creator='" + creator + '\'' +
                ", editors=" + editors +
                ", emb_codes='" + emb_codes + '\'' +
                ", emb_codes_orig='" + emb_codes_orig + '\'' +
                ", emb_codes_tags=" + emb_codes_tags +
                ", expiration_date='" + expiration_date + '\'' +
                ", generic_name='" + generic_name + '\'' +
                ", id='" + id + '\'' +
                ", image_small_url='" + image_small_url + '\'' +
                ", image_url='" + image_url + '\'' +
                ", informers=" + informers +
                ", informers_tags=" + informers_tags +
                ", ingredients=" + ingredients +
                ", ingredients_from_or_that_may_be_from_palm_oil_n=" + ingredients_from_or_that_may_be_from_palm_oil_n +
                ", ingredients_from_palm_oil_n=" + ingredients_from_palm_oil_n +
                ", ingredients_from_palm_oil_tags=" + ingredients_from_palm_oil_tags +
                ", ingredients_tags=" + ingredients_tags +
                ", ingredients_text='" + ingredients_text + '\'' +
                ", ingredients_that_may_be_from_palm_oil_n=" + ingredients_that_may_be_from_palm_oil_n +
                ", ingredients_that_may_be_from_palm_oil_tags=" + ingredients_that_may_be_from_palm_oil_tags +
                ", interface_version_modified='" + interface_version_modified + '\'' +
                ", labels='" + labels + '\'' +
                ", labels_hierarchy=" + labels_hierarchy +
                ", labels_tags=" + labels_tags +
                ", lang='" + lang + '\'' +
                ", last_modified_by='" + last_modified_by + '\'' +
                ", last_modified_t=" + last_modified_t +
                ", lc='" + lc + '\'' +
                ", max_imgid='" + max_imgid + '\'' +
                ", no_nutrition_data=" + no_nutrition_data +
                ", nutrient_levels=" + nutrient_levels +
                ", nutrient_levels_tags=" + nutrient_levels_tags +
                ", nutriments=" + nutriments +
                ", nutrition_data_per='" + nutrition_data_per + '\'' +
                ", origins='" + origins + '\'' +
                ", origins_tags=" + origins_tags +
                ", packaging='" + packaging + '\'' +
                ", packaging_tags=" + packaging_tags +
                ", photographers=" + photographers +
                ", photographers_tags=" + photographers_tags +
                ", product_name='" + product_name + '\'' +
                ", purchase_places='" + purchase_places + '\'' +
                ", purchase_places_tags=" + purchase_places_tags +
                ", quantity='" + quantity + '\'' +
                ", rev=" + rev +
                ", serving_quantity='" + serving_quantity + '\'' +
                ", serving_size='" + serving_size + '\'' +
                ", status='" + status + '\'' +
                ", status_tags=" + status_tags +
                ", stores='" + stores + '\'' +
                ", stores_tags=" + stores_tags +
                ", traces='" + traces + '\'' +
                ", traces_tags=" + traces_tags +
                ", unknown_nutrients_tags=" + unknown_nutrients_tags +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
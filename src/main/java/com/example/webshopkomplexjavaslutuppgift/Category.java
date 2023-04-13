package com.example.webshopkomplexjavaslutuppgift;

public enum Category {
    FRUIT{
        public String toString() {
            return "Fruits";
        }
    }

    ,
    VEGETABLE{
        public String toString() {
            return "Vegetables";
        }
    },
    OTHER{
        public String toString() {
            return "Other";
        }
    }
}

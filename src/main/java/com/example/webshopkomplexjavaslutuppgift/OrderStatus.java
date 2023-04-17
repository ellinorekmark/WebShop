package com.example.webshopkomplexjavaslutuppgift;

public enum OrderStatus {
    NEW {
        public String toString() {
            return "New";
        }
    },
    SENT {
        public String toString() {
            return "Sent";
        }
    },
    RETURNED {
        public String toString() {
            return "Returned";
        }
    }


}

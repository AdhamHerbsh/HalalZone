package com.example.halalzone;

public class PrayerTimesResponse {
    private Data data;

    public Data getData() {
        return data;
    }

    public static class Data {
        private Timings timings;

        public Timings getTimings() {
            return timings;
        }

        public static class Timings {
            private String Fajr;
            private String Sunrise;
            private String Dhuhr;
            private String Asr;
            private String Maghrib;
            private String Isha;

            // Getters
            public String getFajr() {
                return Fajr;
            }

            public String getSunrise() {
                return Sunrise;
            }

            public String getDhuhr() {
                return Dhuhr;
            }

            public String getAsr() {
                return Asr;
            }

            public String getMaghrib() {
                return Maghrib;
            }

            public String getIsha() {
                return Isha;
            }
        }
    }
}

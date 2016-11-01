package architgarg.in.childtracker.DriverList;

// Created by Archit Garg on 02 Oct, 2016.

public class Driver {

      private String name;
      private String contact;
      private String id;
      private String status="0";
      private String date;
      private String time;
      private String routeno;
      private String busno;
      private String lat;
      private String Long;

      public String getContact() {
            return contact;
      }

      public void setContact(String contact) {
            this.contact = contact;
      }

      public String getName() {
            return name;
      }

      public void setName(String name) {
            this.name = name;
      }

      public String getLong() {
            return Long;
      }

      public void setLong(String aLong) {
            Long = aLong;
      }

      public String getLat() {
            return lat;
      }

      public void setLat(String lat) {
            this.lat = lat;
      }

      public String getBusno() {
            return busno;
      }

      public void setBusno(String busno) {
            this.busno = busno;
      }

      public String getRouteno() {
            return routeno;
      }

      public void setRouteno(String routeno) {
            this.routeno = routeno;
      }

      public String getTime() {
            return time;
      }

      public void setTime(String time) {
            this.time = time;
      }

      public String getDate() {
            return date;
      }

      public void setDate(String date) {
            this.date = date;
      }

      public String getStatus() {
            return status;
      }

      public void setStatus(String status) {
            this.status = status;
      }

      public String getId() {
            return id;
      }

      public void setId(String id) {
            this.id = id;
      }
//
//      public Driver(String name, String aLong, String lat, String busno, String routeno, String time, String status, String date, String id, String contact) {
//            this.name = name;
//            Long = aLong;
//            this.lat = lat;
//            this.busno = busno;
//            this.routeno = routeno;
//            this.time = time;
//            this.status = status;
//            this.date = date;
//            this.id = id;
//            this.contact = contact;
//      }
}

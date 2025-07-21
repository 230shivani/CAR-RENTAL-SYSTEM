class Car {
   private String carId;
   private String brand;
   private String model;
   private double pricePerDay;
   private boolean isAvailable;

   public Car(String var1, String var2, String var3, double var4) {
      this.carId = var1;
      this.brand = var2;
      this.model = var3;
      this.pricePerDay = var4;
      this.isAvailable = true;
   }

   public String getCarId() {
      return this.carId;
   }

   public String getBrand() {
      return this.brand;
   }

   public String getModel() {
      return this.model;
   }

   public double getPricePerDay() {
      return this.pricePerDay;
   }

   public boolean isAvailable() {
      return this.isAvailable;
   }

   public void setAvailable(boolean var1) {
      this.isAvailable = var1;
   }

   public String getDisplayInfo() {
      return this.carId + " - " + this.brand + " " + this.model + " ($" + this.pricePerDay + "/day) - " + (this.isAvailable ? "Available" : "Rented");
   }
}

class Customer {
   private String customerId;
   private String name;
   private String licenseNumber;

   public Customer(String var1, String var2, String var3) {
      this.customerId = var1;
      this.name = var2;
      this.licenseNumber = var3;
   }

   public String getCustomerId() {
      return this.customerId;
   }

   public String getName() {
      return this.name;
   }

   public String getLicenseNumber() {
      return this.licenseNumber;
   }
}

class Rental {
   private Customer customer;
   private Car car;
   private int rentalDays;

   public Rental(Customer var1, Car var2, int var3) {
      this.customer = var1;
      this.car = var2;
      this.rentalDays = var3;
   }

   public double calculateTotal() {
      return this.car.getPricePerDay() * (double)this.rentalDays;
   }

   public String getReceipt() {
      String var10000 = this.customer.getName();
      return "Rental Summary:\nCustomer: " + var10000 + "\nCar: " + this.car.getBrand() + " " + this.car.getModel() + "\nDays: " + this.rentalDays + "\nTotal Cost: $" + this.calculateTotal();
   }
}

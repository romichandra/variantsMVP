# variantsMVP
This is a sample solution to an assignment flowed as an android app demonstrating the MVP Design pattern with a network call and an expandable recyclerview.

It lets a User to add/customize variants(Crust,Size,Sause,etc.) to an Order and also take care of variant combinations which have to be excluded across the variants (e.g. Cheese Burst Crust won't combine with Small Size)

The Variants (inclusive of variations and the exclusion list) are fetched from a RESTful service and then parsed up to an expandable recycler view, where the user can customize the order.

External Libraries used to implement the MVP architecture :

/*
   rxjava 1.x, retrofit 2.x and gson 2.x to process HttpRequests */

/*
   dagger 2.2 for Dependency Injection */

/*
   thoughtbot recyclerview */

/*
   android support library for backward compatabilities */

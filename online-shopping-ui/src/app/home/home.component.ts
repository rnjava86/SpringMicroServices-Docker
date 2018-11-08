import { Component, OnInit } from '@angular/core';
import { Product } from '../model/product';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  title="Product Page";
  products:Product[] =[
      { "imageUrl":"assets/images/product.png","price":"700","name":"Product Name1"},
      { "imageUrl":"assets/images/shirt.jpg","price":"500","name":"Product Name2"},
      { "imageUrl":"assets/images/glass.jpg","price":"200","name":"Product Name3"},
      { "imageUrl":"assets/images/product.png","price":"123","name":"Product Name4"}
  ];
  cartProductList: Array<Product> =[
      ];


  wishlistProductList: Array<Product> =[
        ];

    // show product description
    showProduct:boolean=false;
    showProductDescription:Product;
  constructor() { }

  ngOnInit() {


  }
  //view product
  viewProduct(product:Product){
    console.log("view product called..");
    this.showProduct=true;
    this.showProductDescription=product;
    console.log(product);
  }
  //wishlistProduct
  wishlistProduct(product:Product){
    console.log("wishlist called..");
    this.addProduct(product,this.wishlistProductList);
    console.log(this.wishlistProductList);
  }
  //cartProduct
  cartProduct(product:Product){
    console.log("cart Product called..");
    this.addProduct(product,this.cartProductList);
    console.log(this.cartProductList);
  }
  //add product to any array
  addProduct(product:any,arrayObject:Array<any> ){
      length=arrayObject.length;
      arrayObject[length]=product;
  }
}

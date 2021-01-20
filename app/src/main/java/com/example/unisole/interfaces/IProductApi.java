package com.example.unisole.interfaces;

import com.example.unisole.models.Cart;
import com.example.unisole.models.CartItem;
import com.example.unisole.models.CartItemData;
import com.example.unisole.models.Inventory;
import com.example.unisole.models.InventoryMerchantStore;
import com.example.unisole.models.InventoryPK;
import com.example.unisole.models.Order;
import com.example.unisole.models.OrderData;
import com.example.unisole.models.OrderItem;
import com.example.unisole.models.OrderItemData;
import com.example.unisole.models.Token;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IProductApi {
    @GET("products/categoryproducts/{categoryId}")
    Call<List<Inventory>> getProductByCategoryId(@Path("categoryId") String categoryId);
    @GET("inventory/getproducts/{productId}/{sizeId}/{colorId}/{merchantId}")
    Call<InventoryMerchantStore> getProductByInventoryPK(@Path("productId") String productId, @Path("sizeId") int sizeId, @Path("colorId") int colorId, @Path("merchantId") String merchantId);
    @POST("cart/addtocart/{cartId}")
    Call<CartItem> addToCart(@Path("cartId") String cartId, @Body CartItem cartItem);
    @GET("cart/{userId}")
    Call<Cart> getCart(@Path("userId") String userId);
    @GET("cart/get/{cartId}")
    Call<List<CartItemData>>getCartItems(@Path("cartId") String cartId);

    @DELETE("cart/remove/{cartItemId}")
    Call<Token> removeFromCart(@Path("cartItemId") String cartItemId);

    @GET("order/orderhistory/{userId}")
    Call<List<OrderItemData>> getOrderList(@Path("userId") String userId);

    @PUT("order/addrating/{orderItemId}/{rating}")
    Call<Token> addRating(@Path("orderItemId") String orderItemId,@Path("rating") double rating);

    @POST("order/buynow/{userId}")
    Call<Order> buyNow(@Body OrderItem orderItem,@Path("userId") String userId);

    @POST("order/add")
    Call<Order> placeOrder(@Body OrderData orderData);





}

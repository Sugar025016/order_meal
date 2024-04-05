package com.order_meal.order_meal.utils;

import com.order_meal.order_meal.enums.NewErrorStatus;

public class CartDeletionException extends RuntimeException {

    public CartDeletionException() {
        super();
    }

    public CartDeletionException(String message) {
        super(message);
    }

    public CartDeletionException(String message, Throwable cause) {
        super(message, cause);
    }

    public CartDeletionException(Throwable cause) {
        
        super(cause);
    }
    public CartDeletionException(NewErrorStatus errorStatus) {
        
        super(errorStatus.getChinese());
    }


}

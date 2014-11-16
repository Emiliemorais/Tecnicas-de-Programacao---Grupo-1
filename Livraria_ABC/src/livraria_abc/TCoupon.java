
package livraria_abc;

import java.util.ArrayList;
import java.util.List;

// Tipo Cupom de desconto
public class TCoupon
{
    static List<TCoupon> coupons = new ArrayList<>();
    String cupom;
    float valor_desconto;
    
    // Class general constructor
    public TCoupon()
    {
    
    }
    
    // Class constructor
    public TCoupon(String codCoupon, float valueOfCoupon)
    {
        this.cupom = codCoupon;
        this.valor_desconto = valueOfCoupon;
    }
       
    /**
     * Check if the given coupon exists on 
     * @param couponToCheck - Coupon to check 
     * @return true if the coupon exists or false if does not
     */
    public static boolean ChecksCoupon(String couponToCheck)
    {
        int couponIsRegistered = 0;
           
        int quantityOfCoupons = coupons.size(); 
        
        int i = 0;
        for(i = 0; i < quantityOfCoupons; i++)
        {
            String currentCoupon = coupons.get(i).getCoupon();
            boolean currentCouponIsTheOne = currentCoupon.equals(couponToCheck);
            
            if(currentCouponIsTheOne)
            {
                couponIsRegistered++;
                break;
            }
            else
            {
                // Nothing to do
            }
            
        }
            
        boolean couponExists = false;
        if(couponIsRegistered == 0)
        {
            couponExists = false;
        }
        else
        {
            couponExists = true;
        }
            
        return couponExists;
    }
        
    /**
     * Check the discount value of the given coupon
     * @param coupon - Coupon to check
     * @return the discount value of the given coupon or 0 if the coupon was not found
     */
    public static float CheckDiscountValue(String coupon)
    {
        float discountValue = 0;
            
        int i = 0;
            
        int quantityOfCoupons =coupons.size();
        
        for(i = 0; i < quantityOfCoupons; i++)
        {
            String currentCoupon = coupons.get(i).getCoupon();
            
            boolean currentCouponIsTheOne = currentCoupon.equals(coupon);
                    
            if(currentCouponIsTheOne)
            {
                discountValue = coupons.get(i).getValueDiscount();
                break;
            }
            else
            {
                // Nothing to do
            }
        }
          
        return discountValue;
    }
    
    // Getters
    public String getCoupon() 
    {
        return cupom;
    }

    public float getValueDiscount() 
    {
        return valor_desconto;
    }
                
}
    
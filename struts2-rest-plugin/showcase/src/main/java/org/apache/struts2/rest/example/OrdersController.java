package org.apache.struts2.rest.example;

import java.util.Collection;

import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.apache.struts2.dispatcher.ServletActionRedirectResult;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Validateable;
import com.opensymphony.xwork2.ValidationAwareSupport;

@Results({
    @Result(name="success", type=ServletActionRedirectResult.class, value="orders") 
})
public class OrdersController extends ValidationAwareSupport implements ModelDriven<Object>, Validateable{
    
    private Order model = new Order();
    private String id;
    private Collection<Order> list;
    private OrdersService ordersService = new OrdersService();

    public HttpHeaders show() {
        return new DefaultHttpHeaders("show");
    }
    
    public String edit() {
        return "edit";
    }
    
    public String editNew() {
        model = new Order();
        return "editNew";
    }

    public String deleteConfirm() {
        return "deleteConfirm";
    }

    public String destroy() {
        ordersService.remove(id);
        addActionMessage("Order removed successfully");
        return "success";
    }
    
    public HttpHeaders create() {
        ordersService.save(model);
        addActionMessage("New order created successfully");
        return new DefaultHttpHeaders("success")
            .setLocationId(model.getId());
    }
    
    public String update() {
        ordersService.save(model);
        addActionMessage("Order updated successfully");
        return "success";
    }
    
    public HttpHeaders index() {
        list = ordersService.getAll();
        
        return new DefaultHttpHeaders("index")
            .disableCaching();
    }

    public void setId(String id) {
        if (id != null) {
            this.model = ordersService.get(id);
        }
        this.id = id;
    }

    public void validate() {
        if (model.getClientName() == null || model.getClientName().length() ==0) {
            addFieldError("clientName", "The client name is empty");
        }
    }
    
    public Object getModel() {
        return (list != null ? list : model);
    }

}

package model;

public class Goods {
    private Integer id;

    private Integer userId;

    private String name;

    private Float price;

    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescrible() {
        return description;
    }

    public void setDescrible(String describle) {
        this.description = describle == null ? null : describle.trim();
    }
}
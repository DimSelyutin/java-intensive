package com.homework.entity;

import java.io.Serializable;
import java.util.Objects;

public class Fruit implements Serializable {

    private Long id;

    private String name;

    private String color;

    private Double weight;

    // Конструктор по умолчанию
    public Fruit() {
    }

    // Конструктор с параметрами
    public Fruit(String name, String color, Double weight) {
        this.name = name;
        this.color = color;
        this.weight = weight;
    }

    // Геттеры и сеттеры

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    // Переопределение методов equals и hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Fruit fruit = (Fruit) o;
        return Objects.equals(id, fruit.id) &&
                Objects.equals(name, fruit.name) &&
                Objects.equals(color, fruit.color) &&
                Objects.equals(weight, fruit.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, color, weight);
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }
}
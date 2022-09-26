package ua.goit.models;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.Objects;

public class Pet {
    private long id;
    private Category category;
    private String name;
    private String[] photoUrls;
    private Tag[] tags;
    private Status status;

    public enum Status {
        @SerializedName("available")
        AVAILABLE,
        @SerializedName("pending")
        PENDING,
        @SerializedName("sold")
        SOLD
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(String[] photoUrls) {
        this.photoUrls = photoUrls;
    }

    public Tag[] getTags() {
        return tags;
    }

    public void setTags(Tag[] tags) {
        this.tags = tags;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pet)) return false;
        Pet pet = (Pet) o;
        return id == pet.id && Objects.equals(category, pet.category) && Objects.equals(name, pet.name) && Arrays.equals(photoUrls, pet.photoUrls) && Arrays.equals(tags, pet.tags) && status == pet.status;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, category, name, status);
        result = 31 * result + Arrays.hashCode(photoUrls);
        result = 31 * result + Arrays.hashCode(tags);
        return result;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", photoUrls=" + Arrays.toString(photoUrls) +
                ", tags=" + Arrays.toString(tags) +
                ", status=" + status +
                '}';
    }
}

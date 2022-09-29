package ua.goit.models;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;
import java.util.Set;

public class Pet {
    private long id;
    private Category category;
    private String name;
    private Set<String> photoUrls;
    private Set<Tag> tags;
    private Status status;

    public enum Status {
        @SerializedName("available")
        AVAILABLE,
        @SerializedName("pending")
        PENDING,
        @SerializedName("sold")
        SOLD
    }

    public Pet(long id, Category category, String name, Set<String> photoUrls, Set<Tag> tags, Status status) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.photoUrls = photoUrls;
        this.tags = tags;
        this.status = status;
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

    public Set<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(Set<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
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
        return id == pet.id && Objects.equals(category, pet.category) && Objects.equals(name, pet.name) && Objects.equals(photoUrls, pet.photoUrls) && Objects.equals(tags, pet.tags) && status == pet.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, name, photoUrls, tags, status);
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", photoUrls=" + photoUrls +
                ", tags=" + tags +
                ", status=" + status +
                '}';
    }
}

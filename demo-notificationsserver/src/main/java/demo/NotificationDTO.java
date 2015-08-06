package demo;

import java.io.Serializable;
import java.util.Objects;

public class NotificationDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String label;
    private Integer type;
    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "NotificationDto{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", type=" + type +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationDTO that = (NotificationDTO) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getLabel(), that.getLabel()) &&
                Objects.equals(getType(), that.getType()) &&
                Objects.equals(getUrl(), that.getUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLabel(), getType(), getUrl());
    }
}

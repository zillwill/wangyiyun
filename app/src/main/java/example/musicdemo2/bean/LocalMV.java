package example.musicdemo2.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2020/8/3.
 */

public class LocalMV implements Serializable {

    private int id;
    private String data;
    private long size;
    private String displayName;
    private String title;
    private long dateAdded;
    private long dateModified;
    private String mimeType;
    private long duration;
    private String artist;
    private String album;
    private String resolution;
    private String description;
    private int isPrivate;
    private String tags;
    private String category;
    private double latitude;
    private double longitude;
    private int dateTaken;
    private int miniThumbMagic;
    private String bucketId;
    private String bucketDisplayName;
    private int bookmark;

    private String thumbnailData;
    private int kind;
    private long width;
    private long height;

    public void setId(int id) {
        this.id = id;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDateAdded(long dateAdded) {
        this.dateAdded = dateAdded;
    }

    public void setDateModified(long dateModified) {
        this.dateModified = dateModified;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIsPrivate(int isPrivate) {
        this.isPrivate = isPrivate;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setDateTaken(int dateTaken) {
        this.dateTaken = dateTaken;
    }

    public void setMiniThumbMagic(int miniThumbMagic) {
        this.miniThumbMagic = miniThumbMagic;
    }

    public void setBucketId(String bucketId) {
        this.bucketId = bucketId;
    }

    public void setBucketDisplayName(String bucketDisplayName) {
        this.bucketDisplayName = bucketDisplayName;
    }

    public void setBookmark(int bookmark) {
        this.bookmark = bookmark;
    }

    public void setThumbnailData(String thumbnailData) {
        this.thumbnailData = thumbnailData;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public void setWidth(long width) {
        this.width = width;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public long getSize() {
        return size;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getTitle() {
        return title;
    }

    public long getDateAdded() {
        return dateAdded;
    }

    public long getDateModified() {
        return dateModified;
    }

    public String getMimeType() {
        return mimeType;
    }

    public long getDuration() {
        return duration;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public String getResolution() {
        return resolution;
    }

    public String getDescription() {
        return description;
    }

    public int getIsPrivate() {
        return isPrivate;
    }

    public String getTags() {
        return tags;
    }

    public String getCategory() {
        return category;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getDateTaken() {
        return dateTaken;
    }

    public int getMiniThumbMagic() {
        return miniThumbMagic;
    }

    public String getBucketId() {
        return bucketId;
    }

    public String getBucketDisplayName() {
        return bucketDisplayName;
    }

    public int getBookmark() {
        return bookmark;
    }

    public String getThumbnailData() {
        return thumbnailData;
    }

    public int getKind() {
        return kind;
    }

    public long getWidth() {
        return width;
    }

    public long getHeight() {
        return height;
    }
}

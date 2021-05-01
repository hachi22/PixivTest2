package cat.itb.pixiv.ClassesModels;

import android.os.Parcel;
import android.os.Parcelable;

public class MangaClass implements Parcelable {
    String idManga;
    String title;
    String description;
    int likesNumber;
    String userName;
    String mangaImgUrl;
    String userImgUrl;
    boolean imgpriv;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public MangaClass(){

    }

    public MangaClass(String title, String description,  String userName, String mangaImgUrl, String userImgUrl) {
        this.title = title;
        this.description = description;
        this.userName = userName;
        this.mangaImgUrl = mangaImgUrl;
        this.userImgUrl = userImgUrl;
    }

    protected MangaClass(Parcel in) {
        idManga = in.readString();
        title = in.readString();
        description = in.readString();
        likesNumber = in.readInt();
        userName = in.readString();
        mangaImgUrl = in.readString();
        userImgUrl = in.readString();
        imgpriv = in.readByte() != 0;
    }

    public static final Creator<MangaClass> CREATOR = new Creator<MangaClass>() {
        @Override
        public MangaClass createFromParcel(Parcel in) {
            return new MangaClass(in);
        }

        @Override
        public MangaClass[] newArray(int size) {
            return new MangaClass[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idManga);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeInt(likesNumber);
        dest.writeString(userName);
        dest.writeString(mangaImgUrl);
        dest.writeString(userImgUrl);
        dest.writeByte((byte) (imgpriv ? 1 : 0));
    }
    public String getIdManga() {
        return idManga;
    }

    public void setIdManga(String idManga) {
        this.idManga = idManga;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLikesNumber() {
        return likesNumber;
    }

    public void setLikesNumber(int likesNumber) {
        this.likesNumber = likesNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMangaImgUrl() {
        return mangaImgUrl;
    }

    public void setMangaImgUrl(String mangaImgUrl) {
        this.mangaImgUrl = mangaImgUrl;
    }

    public String getUserImgUrl() {
        return userImgUrl;
    }

    public void setUserImgUrl(String userImgUrl) {
        this.userImgUrl = userImgUrl;
    }

    public boolean isImgpriv() {
        return imgpriv;
    }

    public void setImgpriv(boolean imgpriv) {
        this.imgpriv = imgpriv;
    }
}

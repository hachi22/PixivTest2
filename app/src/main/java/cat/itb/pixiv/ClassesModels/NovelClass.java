package cat.itb.pixiv.ClassesModels;

import android.os.Parcel;
import android.os.Parcelable;

public class NovelClass implements Parcelable {
    String novelId;
    int charactersNumbers;
    String title;
    String description;
    String username;
    String content;
    int likesNumber;
    String novelImgUrl;
    String userImgUrl;
    boolean imgpriv;
    private String key;

    public NovelClass(){}

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public NovelClass(int charactersNumbers, String title, String description, String content, String  username, String novelImgUrl, String userImgUrl) {
        this.charactersNumbers = charactersNumbers;
        this.title = title;
        this.description = description;
        this.content = content;
        this.username = username;
        this.novelImgUrl = novelImgUrl;
        this.userImgUrl = userImgUrl;
    }

    protected NovelClass(Parcel in) {
        novelId = in.readString();
        charactersNumbers = in.readInt();
        title = in.readString();
        description = in.readString();
        content = in.readString();
        username = in.readString();
        likesNumber = in.readInt();
        novelImgUrl = in.readString();
        userImgUrl = in.readString();
        imgpriv = in.readByte() != 0;
    }

    public static final Creator<NovelClass> CREATOR = new Creator<NovelClass>() {
        @Override
        public NovelClass createFromParcel(Parcel in) {
            return new NovelClass(in);
        }

        @Override
        public NovelClass[] newArray(int size) {
            return new NovelClass[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(novelId);
        dest.writeInt(charactersNumbers);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(content);
        dest.writeString(username);
        dest.writeInt(likesNumber);
        dest.writeString(novelImgUrl);
        dest.writeString(userImgUrl);
        dest.writeByte((byte) (imgpriv ? 1 : 0));
    }

    public String getNovelId() {
        return novelId;
    }

    public void setNovelId(String novelId) {
        this.novelId = novelId;
    }

    public int getCharactersNumbers() {
        return charactersNumbers;
    }

    public void setCharactersNumbers(int charactersNumbers) {
        this.charactersNumbers = charactersNumbers;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLikesNumber() {
        return likesNumber;
    }

    public void setLikesNumber(int likesNumber) {
        this.likesNumber = likesNumber;
    }

    public String getNovelImgUrl() {
        return novelImgUrl;
    }

    public void setNovelImgUrl(String novelImgUrl) {
        this.novelImgUrl = novelImgUrl;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

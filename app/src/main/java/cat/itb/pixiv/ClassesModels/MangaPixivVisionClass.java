package cat.itb.pixiv.ClassesModels;

import android.os.Parcel;
import android.os.Parcelable;

public class MangaPixivVisionClass implements Parcelable {
    String PixivVisionId;
    String title;
    String pVImgUrl;
    public MangaPixivVisionClass(){

    }

    public MangaPixivVisionClass(String pixivVisionId, String title, String pVImgUrl) {
        PixivVisionId = pixivVisionId;
        this.title = title;
        this.pVImgUrl = pVImgUrl;
    }

    protected MangaPixivVisionClass(Parcel in) {
        PixivVisionId = in.readString();
        title = in.readString();
        pVImgUrl = in.readString();
    }

    public static final Creator<MangaPixivVisionClass> CREATOR = new Creator<MangaPixivVisionClass>() {
        @Override
        public MangaPixivVisionClass createFromParcel(Parcel in) {
            return new MangaPixivVisionClass(in);
        }

        @Override
        public MangaPixivVisionClass[] newArray(int size) {
            return new MangaPixivVisionClass[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(PixivVisionId);
        dest.writeString(title);
        dest.writeString(pVImgUrl);
    }

    public String getPixivVisionId() {
        return PixivVisionId;
    }

    public void setPixivVisionId(String pixivVisionId) {
        PixivVisionId = pixivVisionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getpVImgUrl() {
        return pVImgUrl;
    }

    public void setpVImgUrl(String pVImgUrl) {
        this.pVImgUrl = pVImgUrl;
    }
}

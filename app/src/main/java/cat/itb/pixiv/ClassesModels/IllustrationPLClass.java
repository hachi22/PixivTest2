package cat.itb.pixiv.ClassesModels;

import android.os.Parcel;
import android.os.Parcelable;

public class IllustrationPLClass implements Parcelable {
    String popularLiveId;
    String userName;
    int numViews;
    String userImageUrl;
    String pLImageUrl;
    String Title;

    public IllustrationPLClass(){}

    public IllustrationPLClass(String popularLiveId, String userName, int numViews, String userImageUrl, String pLImageUrl, String title) {
        this.popularLiveId = popularLiveId;
        this.userName = userName;
        this.numViews = numViews;
        this.userImageUrl = userImageUrl;
        this.pLImageUrl = pLImageUrl;
        Title = title;
    }

    protected IllustrationPLClass(Parcel in) {
        popularLiveId = in.readString();
        userName = in.readString();
        numViews = in.readInt();
        userImageUrl = in.readString();
        pLImageUrl = in.readString();
    }

    public static final Creator<IllustrationPLClass> CREATOR = new Creator<IllustrationPLClass>() {
        @Override
        public IllustrationPLClass createFromParcel(Parcel in) {
            return new IllustrationPLClass(in);
        }

        @Override
        public IllustrationPLClass[] newArray(int size) {
            return new IllustrationPLClass[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(popularLiveId);
        dest.writeString(userName);
        dest.writeInt(numViews);
        dest.writeString(userImageUrl);
        dest.writeString(pLImageUrl);
    }

    public String getPopularLiveId() {
        return popularLiveId;
    }

    public void setPopularLiveId(String popularLiveId) {
        this.popularLiveId = popularLiveId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getNumViews() {
        return numViews;
    }

    public void setNumViews(int numViews) {
        this.numViews = numViews;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }

    public String getpLImageUrl() {
        return pLImageUrl;
    }

    public void setpLImageUrl(String pLImageUrl) {
        this.pLImageUrl = pLImageUrl;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}

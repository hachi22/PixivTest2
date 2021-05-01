package cat.itb.pixiv.ClassesModels;

public class ImatgesP {
    private String title;
    private String description;
    private String user;
    private int imageUser;
    private int image;
    private int imagemiddle;
    private  int imageRecomended;
    private int numLikes;
    private int numViews;


    public ImatgesP(String title, String description, String user, int image, int numLikes, int numViews, int imageUser, int imagemiddle,int imageRecomended) {
        this.title = title;
        this.description = description;
        this.user = user;
        this.image = image;
        this.numLikes = numLikes;
        this.numViews = numViews;
        this.imageUser = imageUser;
        this.imagemiddle=imagemiddle;
        this.imageRecomended=imageRecomended;
    }

    public int getImagemiddle() {
        return imagemiddle;
    }

    public void setImagemiddle(int imagemiddle) {
        this.imagemiddle = imagemiddle;
    }

    public int getImageRecomended() {
        return imageRecomended;
    }

    public void setImageRecomended(int imageRecomended) {
        this.imageRecomended = imageRecomended;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(int numLikes) {
        this.numLikes = numLikes;
    }

    public int getNumViews() {
        return numViews;
    }

    public void setNumViews(int numViews) {
        this.numViews = numViews;
    }

    public int getImageUser() {
        return imageUser;
    }

    public void setImageUser(int imageUser) {
        this.imageUser = imageUser;
    }
}

package SpectraSystems.Nexus.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;

@Entity
@Builder
@Table(name = "NEXUSABOUTUS")
public class Aboutus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SLOGAN", length = 500)
    private String slogan;

    @Column(name = "GIF", length = 100)
    private String gif;

    @Column(name = "YT", length = 100)
    private String yt;

    @Column(name = "CARDS_AMOUNT")
    private Integer cardsAmount;

    @Column(name = "TITLE_ONE", length = 100)
    private String titleOne;

    @Column(name = "TEXT_ONE", length = 500)
    private String textOne;

    @Column(name = "IMG_ONE", length = 500)
    private String imgOne;

    @Column(name = "TITLE_TWO", length = 100)
    private String titleTwo;

    @Column(name = "TEXT_TWO", length = 500)
    private String textTwo;

    @Column(name = "IMG_TWO", length = 500)
    private String imgTwo;

    @Column(name = "TITLE_THREE", length = 100)
    private String titleThree;

    @Column(name = "TEXT_THREE", length = 500)
    private String textThree;

    @Column(name = "IMG_THREE", length = 500)
    private String imgThree;

    @Column(name = "TITLE_FOUR", length = 100)
    private String titleFour;

    @Column(name = "TEXT_FOUR", length = 500)
    private String textFour;

    @Column(name = "IMG_FOUR", length = 500)
    private String imgFour;

    public Aboutus(){
        
    }

    public Aboutus(Long id, String slogan, String gif, String yt, Integer cardsAmount, 
                   String titleOne, String textOne, String imgOne, String titleTwo, 
                   String textTwo, String imgTwo, String titleThree, String textThree, 
                   String imgThree, String titleFour, String textFour, String imgFour) {
        this.id = id;
        this.slogan = slogan;
        this.gif = gif;
        this.yt = yt;
        this.cardsAmount = cardsAmount;
        this.titleOne = titleOne;
        this.textOne = textOne;
        this.imgOne = imgOne;
        this.titleTwo = titleTwo;
        this.textTwo = textTwo;
        this.imgTwo = imgTwo;
        this.titleThree = titleThree;
        this.textThree = textThree;
        this.imgThree = imgThree;
        this.titleFour = titleFour;
        this.textFour = textFour;
        this.imgFour = imgFour;
    }
    // Getters and setters for all fields

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getGif() {
        return gif;
    }

    public void setGif(String gif) {
        this.gif = gif;
    }

    public String getYt() {
        return yt;
    }

    public void setYt(String yt) {
        this.yt = yt;
    }

    public Integer getCardsAmount() {
        return cardsAmount;
    }

    public void setCardsAmount(Integer cardsAmount) {
        this.cardsAmount = cardsAmount;
    }

    public String getTitleOne() {
        return titleOne;
    }

    public void setTitleOne(String titleOne) {
        this.titleOne = titleOne;
    }

    public String getTextOne() {
        return textOne;
    }

    public void setTextOne(String textOne) {
        this.textOne = textOne;
    }

    public String getImgOne() {
        return imgOne;
    }

    public void setImgOne(String imgOne) {
        this.imgOne = imgOne;
    }

    public String getTitleTwo() {
        return titleTwo;
    }

    public void setTitleTwo(String titleTwo) {
        this.titleTwo = titleTwo;
    }

    public String getTextTwo() {
        return textTwo;
    }

    public void setTextTwo(String textTwo) {
        this.textTwo = textTwo;
    }

    public String getImgTwo() {
        return imgTwo;
    }

    public void setImgTwo(String imgTwo) {
        this.imgTwo = imgTwo;
    }

    public String getTitleThree() {
        return titleThree;
    }

    public void setTitleThree(String titleThree) {
        this.titleThree = titleThree;
    }

    public String getTextThree() {
        return textThree;
    }

    public void setTextThree(String textThree) {
        this.textThree = textThree;
    }

    public String getImgThree() {
        return imgThree;
    }

    public void setImgThree(String imgThree) {
        this.imgThree = imgThree;
    }

    public String getTitleFour() {
        return titleFour;
    }

    public void setTitleFour(String titleFour) {
        this.titleFour = titleFour;
    }

    public String getTextFour() {
        return textFour;
    }

    public void setTextFour(String textFour) {
        this.textFour = textFour;
    }

    public String getImgFour() {
        return imgFour;
    }

    public void setImgFour(String imgFour) {
        this.imgFour = imgFour;
    }

}

package com.apex.backend;

public class Header {
    String Text_Logo;
    String Section;
    String Link_Section;
    String Link_Profile;
    String Link_Login;
    String Logo; 

    public Header(String Text_Logo,
    String Section,
    String Link_Section,
    String Link_Profile,
    String Link_Login, 
    String Logo) {
        this.Text_Logo = Text_Logo;
        this.Section = Section;
        this.Link_Section = Link_Section;
        this.Link_Profile = Link_Profile;
        this.Link_Login = Link_Login;
        this.Logo = Logo; 

    }
}

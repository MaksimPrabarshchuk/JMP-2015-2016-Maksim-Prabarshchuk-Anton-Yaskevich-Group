package by.epam.mentoring;

class Main {
	public static void main(String args[]) throws CloneNotSupportedException {
        Cookie cookie = new ChokoCookie(50);
        CookieMachine cm = new CookieMachine(cookie);
        System.out.println(String.format("Cookie 1 weight: %d\nCookie 2 weight: %d", cookie.getWeight(), cm.makeCookie().getWeight()));
    }
}
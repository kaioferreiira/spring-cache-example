package com.example.caching;

public class TokenResponse {

	private String isbn;
	private String title;

	public TokenResponse(String isbn, String title) {
		this.isbn = isbn;
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "TokenResponse{" +
				"isbn='" + isbn + '\'' +
				", title='" + title + '\'' +
				'}';
	}
}

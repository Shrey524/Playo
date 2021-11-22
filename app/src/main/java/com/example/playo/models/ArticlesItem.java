package com.example.playo.models;

public class ArticlesItem{
	private String publishedAt;
	private String author;
	private String urlToImage;
	private String description;
	private Source source;
	private String title;
	private String url;
	private String content;

	public String getPublishedAt(){
		return publishedAt;
	}

	public String getAuthor(){
		return author;
	}

	public String getUrlToImage(){
		return urlToImage;
	}

	public String getDescription(){
		return description;
	}

	public Source getSource(){
		return source;
	}

	public String getTitle(){
		return title;
	}

	public String getUrl(){
		return url;
	}

	public String getContent(){
		return content;
	}
}

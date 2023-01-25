package com.jspiders.musicplayerhibernate.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class MusicDTO {
	@Id
	private int id;
	private String songName;
	private String singerName;
	private String movieName;
	private String composer;
	private Double length;
	@Override
	public String toString() {
		return "MusicDTO [id=" + id + ", songName=" + songName + ", singerName=" + singerName + ", movieName="
				+ movieName + ", composer=" + composer + ", length=" + length + "]";
	}


}

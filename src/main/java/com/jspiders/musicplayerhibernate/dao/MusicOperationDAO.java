package com.jspiders.musicplayerhibernate.dao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.TransactionException;
import org.hibernate.query.Query;

import com.jspiders.musicplayerhibernate.dto.MusicDTO;



public class MusicOperationDAO {
	static EntityManagerFactory factory;
	static EntityManager manager;
	static EntityTransaction transaction;
	static Scanner sc=new Scanner(System.in);
	
	public static void openConnection()
	{
		factory=Persistence.createEntityManagerFactory("musicplayer");
		manager=factory.createEntityManager();
		transaction=manager.getTransaction();
	}
	public static void closeConnection()
	{
		if(factory!=null)
		{
			factory.close();
		}
		if(manager!=null)
		{
			manager.close();
		}
		if(transaction!=null)
		{
			try {
				transaction.rollback();
			}catch (TransactionException e) {
				System.out.println("transaction is commited");
			}
		}
	}
	
	public static void showSong()
	{
		try {
			openConnection();
			transaction.begin();
			
			String query="select songName from MusicDTO";
			//MusicDTO music1=manager.find(MusicDTO.class, 9);
			
			Query cquery=(Query) manager.createQuery(query);
			List list=cquery.getResultList();
			if(list.isEmpty())
			{
				System.out.println();
			}
			else
			{
				for(int i=0;i<list.size();i++)
				{
					System.out.println(list.get(i));
					
				}
			}
			//System.out.println(music1);
			transaction.commit();
		} finally {
			closeConnection();
		}

		
	}
	
	public static void addSong() {
		System.out.println("enter no of song to be added");
		int no=sc.nextInt();

		for(int i=1;i<=no;i++)
		{
			try {
				openConnection();
				transaction.begin();
			
			
				MusicDTO music=new MusicDTO();
				
				System.out.println("enter id of song");
				int id=sc.nextInt();
				
				System.out.println("enter name of Song");
				String name=sc.next();
				
				System.out.println("enter name of singer");
				String singer=sc.next();
				
				System.out.println("enter name of movie");
				String movie=sc.next();
				
				System.out.println("enter name of composer");
				String composer=sc.next();
				
				System.out.println("enter length");
				double length=sc.nextDouble();
				
				music.setId(id);
				music.setSongName(name);
				music.setSingerName(singer);
				music.setMovieName(movie);
				music.setComposer(composer);
				music.setLength(length);
				
				manager.persist(music);
				transaction.commit();
			}finally {
				closeConnection();
			}
			
		}
			

		
	}
	
	public static void removeSong()
	{
		
		System.out.println("enter the id of song to be remove");
		int id=sc.nextInt();
		try {
			openConnection();
			transaction.begin();
			MusicDTO music1=manager.find(MusicDTO.class, id);
			
			manager.remove(music1);
			transaction.commit();
			System.out.println("remove success");
		} finally {
			closeConnection();
		}

		
	}
	
	public static void ChooseSongPlay(){
		
		showSong();
		System.out.println("enter the id of song to be play");
		int id=sc.nextInt();
		try {
			openConnection();
			transaction.begin();
			MusicDTO music1=manager.find(MusicDTO.class, id);
			
		
			transaction.commit();
			System.out.println(music1.getSongName()+" is playing");
		} finally {
			closeConnection();
		}

		
	}
	
	public static void playAllSong(){
		
		
		try {
			openConnection();
			transaction.begin();
			//MusicDTO music1=manager.find(MusicDTO.class, id);
			
			
			String query="select songName from MusicDTO";
			//MusicDTO music1=manager.find(MusicDTO.class, 9);
			
			Query cquery=(Query) manager.createQuery(query);
			List list=cquery.getResultList();
			
			for(int i=0;i<list.size();i++)
			{
				System.out.println(list.get(i)+" is playing");
				
			}
		
			transaction.commit();
			//System.out.println(music1.getSongName()+" is playing");
		} finally {
			closeConnection();
		}

		
	}
	
	public static void updateSong() {
		
		System.out.println("choose song id no");
		int id = sc.nextInt();
		System.out.println("1.song name /n2.movie name /n3.composer /n4.length /n5 back");
		int key = sc.nextInt();
		
		try {
			openConnection();
			transaction.begin();
			MusicDTO music1=manager.find(MusicDTO.class, id);
			
		
			switch (key) {
			
			case 1:
				System.out.println("enter song name");
				music1.setSongName(sc.next());
				break;
			case 2:
				System.out.println("enter movie name");
				music1.setMovieName(sc.next());
				break;
			case 3:
				System.out.println("enter composer");
				music1.setComposer(sc.next());
				break;
			case 4:
				System.out.println("enter song by song length");
				music1.setLength(sc.nextDouble());
				break;
			case 5:
				break;
			}
			manager.persist(music1);
			transaction.commit();
			System.out.println("update sucessfully");
		} finally {
			closeConnection();
		}

	
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			musicMenu();	
			
			
	}
	
	public static void musicMenu()
	{
		
		System.out.println("Menu :- \n1.Add/Remove \n2.play \n3.Edit \n4.Exit");
		System.err.println("choose option :-");
		int key = sc.nextInt();
		switch (key) {
		case 1: {
			System.out.println("1.Add song \n2.Remove song \n3. go back");
			System.err.println("choose option :-");
			int key2 = sc.nextInt();
			switch (key2) {
			case 1: {
				addSong();
				break;
			}
			case 2: {
				removeSong();
				break;
			}
			case 3: {

				break;
			}
			}

			
			musicMenu();
			break;
			
		}
		case 2: {
			
			System.out.println("1. choose song \n2. all songs \n3. go back.");
			System.err.println("choose option :-");
			int key1 = sc.nextInt();
			switch (key1) {
			case 1: {
				ChooseSongPlay();
				
				break;
			}
			case 2: {
				playAllSong();
				//opertion.PlayAllSong();
				break;
			}
			
			case 3: {
				break;
			}

			}
			
			musicMenu();
			break;
		
		}
		case 3: {
			updateSong();
			
			musicMenu();
			break;
		}
		case 4: {
			
		
			break;
		}
		}

	}

}

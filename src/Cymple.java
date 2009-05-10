import cymple.data.*;
import cymple.toolkit.*;
import cymple.common.*;

public class Cymple extends Application {
	final private Data data;
	final private Menu users;
	final private Menu artists;
	final private Menu albums;
	final private Menu tracks;

	public Cymple() {
		this("cymple.bin");
	}

	public Cymple(String path) {
		super(1000, 700);
		this.data = new Data(path);
		this.users = new Menu() {
			public void onSelect(SelectItemData item) {
				data.add((User)item);
				data.update();
			}
			public void onUnselect(SelectItemData item) {
				data.remove((User)item);
				data.update();
			}
		};
		this.artists = new Menu() {
			public void onSelect(SelectItemData item) {
				Artist artist = (Artist)item;
				data.add(artist);
				albums.add(artist.getAlbums());
				data.update();
			}
			public void onUnselect(SelectItemData item) {
				Artist artist = (Artist)item;
				data.remove(artist);
				albums.remove(artist.getAlbums());
				data.update();
			}
		};
		this.albums = new Menu() {
			public void onSelect(SelectItemData item) {
				Album album = (Album)item;
				data.add(album);
				tracks.add(album.getTracks());
				data.update();
			}
			public void onUnselect(SelectItemData item) {
				Album album = (Album)item;
				data.remove(album);
				tracks.remove(album.getTracks());
				data.update();
			}
		};
		this.tracks = new Menu() {
			public void onSelect(SelectItemData item) {
				data.add((Track)item);
				data.update();
			}
			public void onUnselect(SelectItemData item) {
				data.remove((Track)item);
				data.update();
			}
		};
	}

	public void setup() {
		super.setup();
		VBox vbox = new VBox();
		Statusbar statusbar = new Statusbar(data);
		TabWrapper tabs = new TabWrapper();
		TabWrapper chartTabs = new TabWrapper();
		HBox hbox = new HBox();
		container.add(vbox);
		vbox.add(hbox, 680);
		vbox.add(statusbar, 20);
		vbox = new VBox();
		hbox.add(vbox, 300);
		vbox.add(tabs, 430);
		VScrollableWrapper s;
		s = new VScrollableWrapper();
		tabs.add(s, "Users");
		s.add(users);
		s = new VScrollableWrapper();
		tabs.add(s, "Artists");
		s.add(artists);
		s = new VScrollableWrapper();
		tabs.add(s, "Albums");
		s.add(albums);
		s = new VScrollableWrapper();
		tabs.add(s, "Tracks");
		s.add(tracks);
		vbox.add(new SeekCircleWrapper(data), 250);
		users.add(data.getUsers());
		artists.add(data.getArtists());
		vbox = new VBox();
		hbox.add(vbox, 700);
		vbox.add(chartTabs, 430);
		vbox.add(new Graph(data), 250);
		VScrollableWrapper vsw;
		Chart chart;
		vsw = new VScrollableWrapper();
		chartTabs.add(vsw, "User");
		chart = new Chart(data);
		vsw.add(chart);
		chart.setKey(ChartKey.User);
		vsw = new VScrollableWrapper();
		chartTabs.add(vsw, "Artist");
		chart = new Chart(data);
		vsw.add(chart);
		chart.setKey(ChartKey.Artist);
		chartTabs.show(vsw);
		vsw = new VScrollableWrapper();
		chartTabs.add(vsw, "Album");
		chart = new Chart(data);
		vsw.add(chart);
		chart.setKey(ChartKey.Album);
		vsw = new VScrollableWrapper();
		chartTabs.add(vsw, "Track");
		chart = new Chart(data);
		vsw.add(chart);
		chart.setKey(ChartKey.Track);
	}

	public static void main(String args[]) {
		Application.main(new String[] {"Cymple"});
	}
}

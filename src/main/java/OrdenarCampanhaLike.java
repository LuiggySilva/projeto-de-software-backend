import java.util.Comparator;

import com.ajude.model.Campanha;

public class OrdenarCampanhaLike implements Comparator<Campanha> {

	@Override
	public int compare(Campanha o1, Campanha o2) {
		return o2.getLikesCount() - o1.getLikesCount();
	}

}

package b_transforming_observables.buffer.switchmap;

/**
 * //todo
 */
final class Item {
	private String searchQuery;

	public Item(final String searchQuery) {
		this.searchQuery = searchQuery;
	}

	@Override
	public String toString() {
		return "Item{" +
				"searchQuery='" + searchQuery + '\'' +
				'}';
	}
}

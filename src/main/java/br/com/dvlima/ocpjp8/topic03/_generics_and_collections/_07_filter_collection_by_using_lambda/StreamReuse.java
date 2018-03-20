package br.com.dvlima.ocpjp8.topic03._generics_and_collections._07_filter_collection_by_using_lambda;

import java.util.stream.IntStream;

public class StreamReuse {

	public static void main(String[] args) {
		IntStream chars = "bookkeep".chars();
		chars.distinct() //
				.sorted() //
				.forEach(ch -> System.out.printf("%c ", ch));
	}

}

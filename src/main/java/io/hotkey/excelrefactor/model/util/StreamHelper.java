package io.hotkey.excelrefactor.model.util;

import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamHelper {

	public static <T> Stream<T> iteratorToFiniteStream(final Iterator<T> iterator){
		final Iterable<T> iterable = () -> iterator;
		return StreamSupport.stream(iterable.spliterator(), false);
	}
}

package src.common;

/**
 * Enum represents tiles to draw
 */
public enum TilesEnum {

	one( "LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL\n"+
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvBvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvBBvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvBBBvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvBBBBvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvBBvBBvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvBBBvvBBvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvBBvvvvBBvvvvvvvvvvvvvL\n" +
					"LvvvvvvvBBvvvvvvBBvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvBBvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvBBvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvBBvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvBBvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvBBvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvBBvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvBBvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvBBvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvBBvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvBBvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvBBvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvBBvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvBBvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvBBvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvBBvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvBBBBBBBBBBBBBBBBvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL\n"),
	two("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL\n"+
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvTTTTTTvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvTTvvvvvTTvvvvvvvvvvvL\n" +
					"LvvvvvvvvvTTvvvvvvvTTvvvvvvvvvvL\n" +
					"LvvvvvvvvvTvvvvvvvvvTvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvTvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvTvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvTvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvTvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvTvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvTvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvTvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvTvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvTvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvTvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvTvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvTvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvTvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvTvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvTvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvTvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvTTTTTTTTTTTTvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL\n"),
	three("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL\n"+
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvRRRRRRRvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvRRvvvvvRRvvvvvvvvvvvL\n" +
					"LvvvvvvvvvRRvvvvvvvRRvvvvvvvvvvL\n" +
					"LvvvvvvvvvRVvvvvvvvvRvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvRvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvRvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvRvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvRvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvRvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvRvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvRRRvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvRRvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvRvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvRvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvRvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvRvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvRvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvRvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvRvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvRvvvvvvvvvvL\n" +
					"LvvvvvvvvvvRvvvvvvvRvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvRRRRRRRvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL\n"),
	four("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL\n"+
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvDDvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvDDDvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvDDvDDvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvDDvvDDvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvDDvvDDvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvDDvvvDDvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvDDvvvvDDvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvDDvvvvvDDvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvDDvvvvvDDvvvvvvvvvvvvL\n" +
					"LvvvvvvvvDDvvvvvvDDvvvvvvvvvvvvL\n" +
					"LvvvvvvvDDvvvvvvvDDvvvvvvvvvvvvL\n" +
					"LvvvvvvDDvvvvvvvvDDvvvvvvvvvvvvL\n" +
					"LvvvvvDDvvvvvvvvvDDvvvvvvvvvvvvL\n" +
					"LvvvvDDvvvvvvvvvvDDvvvvvvvvvvvvL\n" +
					"LvvvDDvvvvvvvvvvvDDvvvvvvvvvvvvL\n" +
					"LvvDDDDDDDDDDDDDDDDDDvvvvvvvvvvL\n" +
					"LvvDDDDDDDDDDDDDDDDvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvDDvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvDDvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvDDvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvDDvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvDDvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvDDvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvDDvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvDDvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL\n"),
	five("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL\n"+
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvIIIIIIIIIIIvvvvvvvvL\n" +
					"LvvvvvvvvvvvIIIIIIIIIIIvvvvvvvvL\n" +
					"LvvvvvvvvvIIvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvIIvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvIIvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvIIvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvIIvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvIIvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvIIIvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvIIIIIIIvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvIIvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvIvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvIvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvIIvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvIIvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvIIvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvIIvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvIIvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvIIvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvIIvvvvvvL\n" +
					"LvvvvvvvvvIvvvvvvvvvvvvIIvvvvvvL\n" +
					"LvvvvvvvvvvIvvvvvvvvvvIvvvvvvvvL\n" +
					"LvvvvvvvvvvIIIIvvvvvIIvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvIIIIIIvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL\n"),
	six("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL\n"+
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvKKKKKvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvKKvvvvvKvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvKKvvvvvvvKvvvvvvvvL\n" +
					"LvvvvvvvvvvvKKvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvKKKvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvKKKvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvKKKvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvKKvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvKKvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvKKKvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvKKvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvKKvKKKKKKKvvvvvvvvvvvvvL\n" +
					"LvvvvvvvKKKvvvvvvvKKvvvvvvvvvvvL\n" +
					"LvvvvvvvKKvvvvvvvvvvKvvvvvvvvvvL\n" +
					"LvvvvvvKKvvvvvvvvvvvvKvvvvvvvvvL\n" +
					"LvvvvvvKKvvvvvvvvvvvvvKKvvvvvvvL\n" +
					"LvvvvvvKKvvvvvvvvvvvvvKKvvvvvvvL\n" +
					"LvvvvvvKKvvvvvvvvvvvvvKKvvvvvvvL\n" +
					"LvvvvvvKKvvvvvvvvvvvvvKKvvvvvvvL\n" +
					"LvvvvvvKKvvvvvvvvvvvvvKKvvvvvvvL\n" +
					"LvvvvvvKKvvvvvvvvvvvvvKKvvvvvvvL\n" +
					"LvvvvvvKKKvvvvvvvvvvvvKKvvvvvvvL\n" +
					"LvvvvvvvvKvvvvvvvvvvvvKKvvvvvvvL\n" +
					"LvvvvvvvvvKKvvvvvvvvvKKvvvvvvvvL\n" +
					"LvvvvvvvvvvKKKvvvvvKKKvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvKKKKKKvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL\n"),
	seven("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL\n"+
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvMMMMMMMMMMMMMMMvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvMvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvMvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvMvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvMvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvMvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvMvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvMvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvMvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvMvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvMvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvMvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvMvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvMMvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvMvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvMvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvMvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvMvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvMvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvMvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvMvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvMvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvMvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvMvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL\n"),
	eight("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL\n"+
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvPPPPPPvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvPPvvvvPPvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvPPvvvvvvPPvvvvvvvvvvvL\n" +
					"LvvvvvvvvvPvvvvvvvvPvvvvvvvvvvvL\n" +
					"LvvvvvvvvvPvvvvvvvvPvvvvvvvvvvvL\n" +
					"LvvvvvvvvvPvvvvvvvVPvvvvvvvvvvvL\n" +
					"LvvvvvvvvvPvvvvvvvVPvvvvvvvvvvvL\n" +
					"LvvvvvvvvvPPvvvvvvPPvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvPvvvvvvPvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvPPvvvvPPvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvPPvvPPvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvPPPPvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvPPPPvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvPPvvPPvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvPPvvvvPPvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvPPvvvvvvPPvvvvvvvvvvvL\n" +
					"LvvvvvvvvPPvvvvvvvvPPvvvvvvvvvvL\n" +
					"LvvvvvvvvPVvvvvvvvvvPvvvvvvvvvvL\n" +
					"LvvvvvvvPPvvvvvvvvvvPPvvvvvvvvvL\n" +
					"LvvvvvvvPvvvvvvvvvvvvPvvvvvvvvvL\n" +
					"LvvvvvvvPvvvvvvvvvvvvPvvvvvvvvvL\n" +
					"LvvvvvvvPvvvvvvvvvvvvPvvvvvvvvvL\n" +
					"LvvvvvvvPPvvvvvvvvvvPPvvvvvvvvvL\n" +
					"LvvvvvvvvPPvvvvvvvvPPvvvvvvvvvvL\n" +
					"LvvvvvvvvvPPvvvvvvPPvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvPPPPPPvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL\n"),
	MINE_IMG("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL\n"+
					"LWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWWLLLLLLLWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWLLLLLLLLLWWWWWWWWWWL\n" +
					"LWWWWWWWWWWLLLLLLLLLLLWWWWWWWWWL\n" +
					"LWWWWWWWWWLLWWLLLLLLLLLWWWWWWWWL\n" +
					"LWWWWWWWWLLWWWLLLLLLLLLLWWWWWWWL\n" +
					"LWWWWWWWLLWWWWLLLLLLLLLLLWWWWWWL\n" +
					"LWWWWWWWLLWWWLLLLLLLLLLLLWWWWWWL\n" +
					"LWWWWWWLLLWWLLLLLLLLLLLLLWWWWWWL\n" +
					"LWWWWWWLLLWLLLLLLLLLLLLLLWWWWWWL\n" +
					"LWWWWWWLLLLLLLLLLLLLLLLLLWWWWWWL\n" +
					"LWWWWWWLLLLLLLLLLLLLLLLLLWWWWWWL\n" +
					"LWWWWWWWLLLLLLLLLLLLLLLLLWWWWWWL\n" +
					"LWWWWWWWLLLLLLLLLLLLLLLLWWWWWWWL\n" +
					"LWWWWWWWWLLLLLLLLLLLLLLWWWWWWWWL\n" +
					"LWWWWWWWWWLLLLLLLLLLLLWWWWWWWWWL\n" +
					"LWWWWWWWWWWLLLLLLLLLLWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWLLLLLLLLWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWWLLLLLLWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWL\n" +
					"LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL\n"),
	BLOW_IMG("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL\n"+
					"LWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWL\n" +
					"LWWRWWWWWWWWWWWWWWWWWWWWWWWWRWWL\n" +
					"LWWWRWWWWWWWWWWWWWWWWWWWWWWRWWWL\n" +
					"LWWWWRWWWWWWWWWWWWWWWWWWWWRWWWWL\n" +
					"LWWWWWRWWWWWWWWWWWWWWWWWWRWWWWWL\n" +
					"LWWWWWWRWWWWWLLLLLLLWWWWRWWWWWWL\n" +
					"LWWWWWWWRWWWLLLLLLLLLWWRWWWWWWWL\n" +
					"LWWWWWWWWRWLLLLLLLLLLLRWWWWWWWWL\n" +
					"LWWWWWWWWWRLWWLLLLLLLRLWWWWWWWWL\n" +
					"LWWWWWWWWLLRWWLLLLLLRLLLWWWWWWWL\n" +
					"LWWWWWWWLLWWRWLLLLLRLLLLLWWWWWWL\n" +
					"LWWWWWWWLLWWWRLLLLRLLLLLLWWWWWWL\n" +
					"LWWWWWWLLLWWLLRLLRLLLLLLLWWWWWWL\n" +
					"LWWWWWWLLLWLLLLRRLLLLLLLLWWWWWWL\n" +
					"LWWWWWWLLLLLLLLRRLLLLLLLLWWWWWWL\n" +
					"LWWWWWWLLLLLLLRLLRLLLLLLLWWWWWWL\n" +
					"LWWWWWWWLLLLLRLLLLRLLLLLLWWWWWWL\n" +
					"LWWWWWWWLLLLRLLLLLLRLLLLWWWWWWWL\n" +
					"LWWWWWWWWLLRLLLLLLLLRLLWWWWWWWWL\n" +
					"LWWWWWWWWLRLLLLLLLLLLRWWWWWWWWWL\n" +
					"LWWWWWWWWRWLLLLLLLLLLWRWWWWWWWWL\n" +
					"LWWWWWWWRWWWLLLLLLLLWWWRWWWWWWWL\n" +
					"LWWWWWWRWWWWWLLLLLLWWWWWRWWWWWWL\n" +
					"LWWWWWRWWWWWWWWWWWWWWWWWWRWWWWWL\n" +
					"LWWWWRWWWWWWWWWWWWWWWWWWWWRWWWWL\n" +
					"LWWWRWWWWWWWWWWWWWWWWWWWWWWRWWWL\n" +
					"LWWRWWWWWWWWWWWWWWWWWWWWWWWWRWWL\n" +
					"LWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWL\n" +
					"LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL\n"),
	MARK_IMG("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL\n"+
					"LWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWL\n" +
					"LWWWWWWLLLLLLLLLLLLWWWWWWWWWWWWL\n" +
					"LWWWWWWWLRRRRRRRRRLWWWWWWWWWWWWL\n" +
					"LWWWWWWWWLRRRRRRRRLWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWLRRRRRRRLWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWLRRRRRRLWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWLRRRRRRRLWWWWWWWWWWWWL\n" +
					"LWWWWWWWWLRRRRRRRRLWWWWWWWWWWWWL\n" +
					"LWWWWWWWLRRRRRRRRRLWWWWWWWWWWWWL\n" +
					"LWWWWWWLLLLLLLLLLLLWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWWWWWWWLWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWWWWWWWLWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWWWWWWWLWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWWWWWWWLWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWWWWWWWLWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWWWWWWWLWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWWWWWWWLWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWWWWWWWLWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWWWWWWWLWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWWWWWWWLWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWWWWWWWLWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWLLLLLLLLLLLLLLLLWWWWWL\n" +
					"LWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWL\n" +
					"LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL\n"),

	WRONG_MARK_IMG("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL\n"+
					"LWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWWWWWWWWWWWWWWWWWLWWL\n" +
					"LWWWWWWWWWWWWWWWWWWWWWWWWWWLWWWL\n" +
					"LWWWWWWWWWWWWWWWWWWWWWWWWWLWWWWL\n" +
					"LWWWWWLLLLLLLLLLLLWWWWWWWLWWWWWL\n" +
					"LWWWWWWLRRRRRRRRRLWWWWWWLWWWWWWL\n" +
					"LWWWWWWWLRRRRRRRRLWWWWWLWWWWWWWL\n" +
					"LWWWWWWWWLRRRRRRRLWWWWLWWWWWWWWL\n" +
					"LWWWWWWWWWLRRRRRRLWWWLWWWWWWWWWL\n" +
					"LWWWWWWWWLRRRRRRRLWWLWWWWWWWWWWL\n" +
					"LWWWWWWWLRRRRRRRRLWLWWWWWWWWWWWL\n" +
					"LWWWWWWLRRRRRRRRRLLWWWWWWWWWWWWL\n" +
					"LWWWWWLLLLLLLLLLLLWWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWWWWWLLWWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWWWWLWLWWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWWWLWWLWWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWWLWWWLWWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWLWWWWLWWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWLWWWWWLWWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWLWWWWWWLWWWWWWWWWWWWWL\n" +
					"LWWWWWWWWLWWWWWWWLWWWWWWWWWWWWWL\n" +
					"LWWWWWWWLWWWWWWWWLWWWWWWWWWWWWWL\n" +
					"LWWWWWWLWWWWWWWWWLWWWWWWWWWWWWWL\n" +
					"LWWWWWLWWWWWWWWWWLWWWWWWWWWWWWWL\n" +
					"LWWWWLWWWLLLLLLLLLLLLLLLLWWWWWWL\n" +
					"LWWWLWWWWWWWWWWWWWWWWWWWWWWWWWWL\n" +
					"LWWLWWWWWWWWWWWWWWWWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWL\n" +
					"LWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWL\n" +
					"LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL\n"),

	COVER_IMG("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL\n"+
					"LWWWWWWWWWWWWWWWWWWWWWWWWWWWWWLL\n" +
					"LWWWWWWWWWWWWWWWWWWWWWWWWWWWWWLL\n" +
					"LWWAAAAAAAAAAAAAAAAAAAAAAAAAAALL\n" +
					"LWWAAAAAAAAAAAAAAAAAAAAAAAAAAALL\n" +
					"LWWAAAAAAAAAAAAAAAAAAAAAAAAAAALL\n" +
					"LWWAAAAAAAAAAAAAAAAAAAAAAAAAAALL\n" +
					"LWWAAAAAAAAAAAAAAAAAAAAAAAAAAALL\n" +
					"LWWAAAAAAAAAAAAAAAAAAAAAAAAAAALL\n" +
					"LWWAAAAAAAAAAAAAAAAAAAAAAAAAAALL\n" +
					"LWWAAAAAAAAAAAAAAAAAAAAAAAAAAALL\n" +
					"LWWAAAAAAAAAAAAAAAAAAAAAAAAAAALL\n" +
					"LWWAAAAAAAAAAAAAAAAAAAAAAAAAAALL\n" +
					"LWWAAAAAAAAAAAAAAAAAAAAAAAAAAALL\n" +
					"LWWAAAAAAAAAAAAAAAAAAAAAAAAAAALL\n" +
					"LWWAAAAAAAAAAAAAAAAAAAAAAAAAAALL\n" +
					"LWWAAAAAAAAAAAAAAAAAAAAAAAAAAALL\n" +
					"LWWAAAAAAAAAAAAAAAAAAAAAAAAAAALL\n" +
					"LWWAAAAAAAAAAAAAAAAAAAAAAAAAAALL\n" +
					"LWWAAAAAAAAAAAAAAAAAAAAAAAAAAALL\n" +
					"LWWAAAAAAAAAAAAAAAAAAAAAAAAAAALL\n" +
					"LWWAAAAAAAAAAAAAAAAAAAAAAAAAAALL\n" +
					"LWWAAAAAAAAAAAAAAAAAAAAAAAAAAALL\n" +
					"LWWAAAAAAAAAAAAAAAAAAAAAAAAAAALL\n" +
					"LWWAAAAAAAAAAAAAAAAAAAAAAAAAAALL\n" +
					"LWWAAAAAAAAAAAAAAAAAAAAAAAAAAALL\n" +
					"LWWAAAAAAAAAAAAAAAAAAAAAAAAAAALL\n" +
					"LWWAAAAAAAAAAAAAAAAAAAAAAAAAAALL\n" +
					"LWWAAAAAAAAAAAAAAAAAAAAAAAAAAALL\n" +
					"LWWAAAAAAAAAAAAAAAAAAAAAAAAAAALL\n" +
					"LWWLLLLLLLLLLLLLLLLLLLLLLLLLLLLL\n" +
					"LWWLLLLLLLLLLLLLLLLLLLLLLLLLLLLL\n"),

	NULL("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvL\n" +
					"LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL\n")

	;
	/**
	 * title field containing chars to draw
	 */
	private final String title;

	/**
	 * TilesEnum contructor
	 * @param s enum value
	 */
	TilesEnum(String s) {
		title = s;
	}

	/**
	 * getter for enum value
	 * @return string of chars to draw
	 */
	public String getTitle() {
		return title;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("TilesEnum{");
			sb.append("title='").append(title);
			sb.append('}');
			return sb.toString();
	}
}

package scala.compat.java8

import language.implicitConversions

import java.util.stream._
import scala.compat.java8.collectionImpl._
import scala.compat.java8.converterImpl._

trait PrimitiveStreamAccumulator[S, AA] {
  def streamAccumulate(stream: S): AA
}

trait PrimitiveStreamUnboxer[A, S] {
  def apply(boxed: Stream[A]): S
}

trait Priority3StreamConverters {
  implicit class EnrichAnySteppableWithParStream[A <: AnyRef, CC](cc: CC)(implicit steppize: CC => MakesStepper[AnyStepper[A] with EfficientSubstep])
  extends MakesParallelStream[A, Stream[A]] {
    def parStream: Stream[A] = StreamSupport.stream(steppize(cc).stepper.anticipateParallelism, true)
  }
  implicit class EnrichAnyKeySteppableWithParKeyStream[K, CC](cc: CC)(implicit steppize: CC => MakesKeyStepper[AnyStepper[K] with EfficientSubstep]) {
    def parKeyStream: Stream[K] = StreamSupport.stream(steppize(cc).keyStepper.anticipateParallelism, true)
  }
  implicit class EnrichAnyValueSteppableWithParValueStream[V, CC](cc: CC)(implicit steppize: CC => MakesValueStepper[AnyStepper[V] with EfficientSubstep]) {
    def parValueStream: Stream[V] = StreamSupport.stream(steppize(cc).valueStepper.anticipateParallelism, true)
  }
  // Note--conversion is only to make sure implicit conversion priority is lower than alternatives.
  implicit class EnrichScalaCollectionWithSeqStream[A <: AnyRef, CC](cc: CC)(implicit steppize: CC => MakesStepper[AnyStepper[A]])
    extends MakesSequentialStream[A, Stream[A]] {
    def seqStream: Stream[A] = StreamSupport.stream(steppize(cc).stepper, false)
  }
  implicit class EnrichScalaCollectionWithKeySeqStream[K, CC](cc: CC)(implicit steppize: CC => MakesKeyStepper[AnyStepper[K]]) {
    def seqKeyStream: Stream[K] = StreamSupport.stream(steppize(cc).keyStepper, false)
  }
  implicit class EnrichScalaCollectionWithValueSeqStream[V, CC](cc: CC)(implicit steppize: CC => MakesValueStepper[AnyStepper[V]]) {
    def seqValueStream: Stream[V] = StreamSupport.stream(steppize(cc).valueStepper, false)
  }
}

trait Priority2StreamConverters extends Priority3StreamConverters {
  implicit class EnrichDoubleSteppableWithParStream[CC](cc: CC)(implicit steppize: CC => MakesStepper[DoubleStepper with EfficientSubstep])
  extends MakesParallelStream[java.lang.Double, DoubleStream] {
    def parStream: DoubleStream = StreamSupport.doubleStream(steppize(cc).stepper.anticipateParallelism, true)
  }
  implicit class EnrichDoubleKeySteppableWithParKeyStream[CC](cc: CC)(implicit steppize: CC => MakesKeyStepper[DoubleStepper with EfficientSubstep]) {
    def parKeyStream: DoubleStream = StreamSupport.doubleStream(steppize(cc).keyStepper.anticipateParallelism, true)
  }
  implicit class EnrichDoubleValueSteppableWithParValueStream[CC](cc: CC)(implicit steppize: CC => MakesValueStepper[DoubleStepper with EfficientSubstep]) {
    def parValueStream: DoubleStream = StreamSupport.doubleStream(steppize(cc).valueStepper.anticipateParallelism, true)
  }
  implicit class EnrichIntSteppableWithParStream[CC](cc: CC)(implicit steppize: CC => MakesStepper[IntStepper with EfficientSubstep])
  extends MakesParallelStream[java.lang.Integer, IntStream] {
    def parStream: IntStream = StreamSupport.intStream(steppize(cc).stepper.anticipateParallelism, true)
  }
  implicit class EnrichIntKeySteppableWithParKeyStream[CC](cc: CC)(implicit steppize: CC => MakesKeyStepper[IntStepper with EfficientSubstep]) {
    def parKeyStream: IntStream = StreamSupport.intStream(steppize(cc).keyStepper.anticipateParallelism, true)
  }
  implicit class EnrichIntValueSteppableWithParValueStream[CC](cc: CC)(implicit steppize: CC => MakesValueStepper[IntStepper with EfficientSubstep]) {
    def parValueStream: IntStream = StreamSupport.intStream(steppize(cc).valueStepper.anticipateParallelism, true)
  }
  implicit class EnrichLongSteppableWithParStream[CC](cc: CC)(implicit steppize: CC => MakesStepper[LongStepper with EfficientSubstep])
  extends MakesParallelStream[java.lang.Long, LongStream] {
    def parStream: LongStream = StreamSupport.longStream(steppize(cc).stepper.anticipateParallelism, true)
  }
  implicit class EnrichLongKeySteppableWithParKeyStream[CC](cc: CC)(implicit steppize: CC => MakesKeyStepper[LongStepper with EfficientSubstep]) {
    def parKeyStream: LongStream = StreamSupport.longStream(steppize(cc).keyStepper.anticipateParallelism, true)
  }
  implicit class EnrichLongValueSteppableWithParValueStream[CC](cc: CC)(implicit steppize: CC => MakesValueStepper[LongStepper with EfficientSubstep]) {
    def parValueStream: LongStream = StreamSupport.longStream(steppize(cc).valueStepper.anticipateParallelism, true)
  }
  implicit class EnrichScalaCollectionWithSeqDoubleStream[CC](cc: CC)(implicit steppize: CC => MakesStepper[DoubleStepper])
    extends MakesSequentialStream[java.lang.Double, DoubleStream] {
    def seqStream: DoubleStream = StreamSupport.doubleStream(steppize(cc).stepper, false)
  }
  implicit class EnrichScalaCollectionWithSeqIntStream[CC](cc: CC)(implicit steppize: CC => MakesStepper[IntStepper])
    extends MakesSequentialStream[java.lang.Integer, IntStream] {
    def seqStream: IntStream = StreamSupport.intStream(steppize(cc).stepper, false)
  }
  implicit class EnrichScalaCollectionWithSeqLongStream[CC](cc: CC)(implicit steppize: CC => MakesStepper[LongStepper])
    extends MakesSequentialStream[java.lang.Long, LongStream] {
    def seqStream: LongStream = StreamSupport.longStream(steppize(cc).stepper, false)
  }
  implicit class EnrichScalaCollectionWithSeqDoubleKeyStream[CC](cc: CC)(implicit steppize: CC => MakesKeyStepper[DoubleStepper]) {
    def seqKeyStream: DoubleStream = StreamSupport.doubleStream(steppize(cc).keyStepper, false)
  }
  implicit class EnrichScalaCollectionWithSeqIntKeyStream[CC](cc: CC)(implicit steppize: CC => MakesKeyStepper[IntStepper]) {
    def seqKeyStream: IntStream = StreamSupport.intStream(steppize(cc).keyStepper, false)
  }
  implicit class EnrichScalaCollectionWithSeqLongKeyStream[CC](cc: CC)(implicit steppize: CC => MakesKeyStepper[LongStepper]) {
    def seqKeyStream: LongStream = StreamSupport.longStream(steppize(cc).keyStepper, false)
  }
  implicit class EnrichScalaCollectionWithSeqDoubleValueStream[CC](cc: CC)(implicit steppize: CC => MakesValueStepper[DoubleStepper]) {
    def seqValueStream: DoubleStream = StreamSupport.doubleStream(steppize(cc).valueStepper, false)
  }
  implicit class EnrichScalaCollectionWithSeqIntValueStream[CC](cc: CC)(implicit steppize: CC => MakesValueStepper[IntStepper]) {
    def seqValueStream: IntStream = StreamSupport.intStream(steppize(cc).valueStepper, false)
  }
  implicit class EnrichScalaCollectionWithSeqLongValueStream[CC](cc: CC)(implicit steppize: CC => MakesValueStepper[LongStepper]) {
    def seqValueStream: LongStream = StreamSupport.longStream(steppize(cc).valueStepper, false)
  }
}

trait Priority1StreamConverters extends Priority2StreamConverters {
  implicit class RichStream[A](stream: Stream[A]) {
    def accumulate = stream.collect(Accumulator.supplier[A], Accumulator.adder[A], Accumulator.merger[A])
    
    def toScala[Coll[_]](implicit cbf: collection.generic.CanBuildFrom[Nothing, A, Coll[A]]): Coll[A] = {
      if (stream.isParallel) accumulate.to[Coll](cbf)
      else {
        val b = cbf()
        stream.forEachOrdered(new java.util.function.Consumer[A]{ def accept(a: A) { b += a } })
        b.result()
      }
    }
    
    def unboxed[S](implicit ubx: PrimitiveStreamUnboxer[A, S]): S = ubx(stream)
  }
  
  implicit class RichStreamCanAccumulatePrimitive[S](stream: S) {
    def accumulatePrimitive[AA](implicit psa: PrimitiveStreamAccumulator[S, AA]) = psa.streamAccumulate(stream)
  }
}

/** `StreamConverters` provides extension methods and other functionality to
  * ease interoperability of Scala collections with `java.util.stream` classes.
  * 
  * Scala collections gain extension methods `seqStream` and
  * `parStream` that allow them to be used as the source of a `Stream`.
  * Some collections either intrinsically cannot be paralellized, or
  * could be but an efficient implementation is missing.  It this case,
  * only `seqStream` is provided.  If a collection cannot be stepped over
  * at all (e.g. `Traversable`), then it gains neither method.
  *
  * `Array` also gains `seqStream` and `parStream` methods, and calling those
  * on `Array[Double]`, `Array[Int]`, or `Array[Long]` will produce the
  * corresponding primitive stream.
  *
  * Streams gain `accumulate` and `toScala[_]` methods, which collect the stream
  * into a custom high-performance `scala.collection.mutable.java8.Accumulator`,
  * which is not part of the standard collections hierarchy, or into a named
  * Scala collection, respectively.
  *
  * Generic streams also gain an `unboxed` method that will convert to the
  * corresponding unboxed primitive stream, if appropriate.  Unboxed streams
  * have custom accumulators with improved performance.
  *
  * Accumulators have `toArray`, `toList`, `iterator`, and `to[_]` methods
  * to convert to standard Scala collections.  Note that if you wish to
  * create an array from a `Stream`, going through an `Accumulator` is
  * not the most efficient option: just create the `Array` directly.
  *
  * Internally, Scala collections implement a hybrid of `Iterator` and
  * `java.util.Spliterator` to implement `Stream` compatibility; these
  * are called `Stepper`s.  In particular, they can test for the presence
  * of a next element using `hasStep`, can retrieve the next value with
  * `nextStep`, or can optionally retrieve and operate on a value if present
  * with `tryStep`, which works like `tryAdvance` in `java.util.Spliterator`.
  *
  * Every Scala collection that can be stepped
  * through has a `stepper` method implicitly provided.  In addition,
  * maps have `keyStepper` and `valueStepper` methods.  A limited number
  * of collections operations are defined on `Stepper`s, including conversion
  * to Scala collections with `to` or accumulation via `accumulate`.
  * `Stepper`s also implement `seqStream` and `parStream` to generate `Stream`s.
  * These are provided regardless of whether a `Stepper` can efficiently
  * subdivide itself for parallel processing (though one can check for the
  * presence of the `EfficientSubstep` trait to know that parallel execution will
  * not be limited by long sequential searching steps, and one can call
  * `anticipateParallelism` to warn a `Stepper` that it will be used in a parallel
  * context and thus may wish to make different tradeoffs).
  *
  * Examples:
  * {{{
  * import scala.compat.java8.StreamConverers._
  *
  * val s = Vector(1,2,3,4).parStream    // Stream[Int]
  * val si = s.unboxed                   // Stream.OfInt
  * val ai = si.accumulate               // IntAccumulator
  * val v = ai.to[Vector]                // Vector[Int] again
  *
  * val t = Array(2.0, 3.0, 4.0).parStream               // DoubleStream
  * val q = t.toScala[scala.collection.immutable.Queue]  // Queue[Double]
  *
  * val x = List(1L, 2L, 3L, 4L).stepper.parStream.sum   // 10, potentially computed in parallel
  * }}}
  */
object StreamConverters
extends Priority1StreamConverters
with converterImpl.Priority1StepConverters
with converterImpl.Priority1AccumulatorConverters
{
  implicit final class EnrichDoubleArrayWithStream(private val a: Array[Double])
  extends AnyVal with MakesSequentialStream[java.lang.Double, DoubleStream] with MakesParallelStream[java.lang.Double, DoubleStream] {
    def seqStream: DoubleStream = java.util.Arrays.stream(a)
    def parStream: DoubleStream = seqStream.parallel
  }

  implicit final class EnrichIntArrayWithStream(private val a: Array[Int])
  extends AnyVal with MakesSequentialStream[java.lang.Integer, IntStream] with MakesParallelStream[java.lang.Integer, IntStream] {
    def seqStream: IntStream = java.util.Arrays.stream(a)
    def parStream: IntStream = seqStream.parallel
  }

  implicit final class EnrichLongArrayWithStream(private val a: Array[Long])
  extends AnyVal with MakesSequentialStream[java.lang.Long, LongStream] with MakesParallelStream[java.lang.Long, LongStream] {
    def seqStream: LongStream = java.util.Arrays.stream(a)
    def parStream: LongStream = seqStream.parallel
  }

  implicit val primitiveAccumulateDoubleStream = new PrimitiveStreamAccumulator[Stream[Double], DoubleAccumulator] {
    def streamAccumulate(stream: Stream[Double]): DoubleAccumulator = 
      stream.collect(DoubleAccumulator.supplier, DoubleAccumulator.boxedAdder, DoubleAccumulator.merger)
  }
  
  implicit val primitiveAccumulateDoubleStream2 =
    primitiveAccumulateDoubleStream.asInstanceOf[PrimitiveStreamAccumulator[Stream[java.lang.Double], DoubleAccumulator]]
    
  implicit val primitiveUnboxDoubleStream = new PrimitiveStreamUnboxer[Double, DoubleStream] {
    def apply(boxed: Stream[Double]): DoubleStream = 
      boxed.mapToDouble(new java.util.function.ToDoubleFunction[Double]{ def applyAsDouble(d: Double) = d })
  }
  
  implicit val primitiveUnboxDoubleStream2 =
    primitiveUnboxDoubleStream.asInstanceOf[PrimitiveStreamUnboxer[java.lang.Double, DoubleStream]]
  
  implicit val primitiveAccumulateIntStream = new PrimitiveStreamAccumulator[Stream[Int], IntAccumulator] { 
    def streamAccumulate(stream: Stream[Int]): IntAccumulator = 
      stream.collect(IntAccumulator.supplier, IntAccumulator.boxedAdder, IntAccumulator.merger)
  }

  implicit val primitiveAccumulateIntStream2 =
    primitiveAccumulateIntStream.asInstanceOf[PrimitiveStreamAccumulator[Stream[java.lang.Integer], IntAccumulator]]
  
  implicit val primitiveUnboxIntStream = new PrimitiveStreamUnboxer[Int, IntStream] {
    def apply(boxed: Stream[Int]): IntStream = 
      boxed.mapToInt(new java.util.function.ToIntFunction[Int]{ def applyAsInt(d: Int) = d })
  }
  
  implicit val primitiveUnboxIntStream2 =
    primitiveUnboxIntStream.asInstanceOf[PrimitiveStreamUnboxer[java.lang.Integer, IntStream]]
  
  implicit val primitiveAccumulateLongStream = new PrimitiveStreamAccumulator[Stream[Long], LongAccumulator] { 
    def streamAccumulate(stream: Stream[Long]): LongAccumulator = 
      stream.collect(LongAccumulator.supplier, LongAccumulator.boxedAdder, LongAccumulator.merger)
  }

  implicit val primitiveAccumulateLongStream2 =
    primitiveAccumulateLongStream.asInstanceOf[PrimitiveStreamAccumulator[Stream[java.lang.Long], LongAccumulator]]
  
  implicit val primitiveUnboxLongStream = new PrimitiveStreamUnboxer[Long, LongStream] {
    def apply(boxed: Stream[Long]): LongStream = 
      boxed.mapToLong(new java.util.function.ToLongFunction[Long]{ def applyAsLong(d: Long) = d })
  }
  
  implicit val primitiveUnboxLongStream2 =
    primitiveUnboxLongStream.asInstanceOf[PrimitiveStreamUnboxer[java.lang.Long, LongStream]]
  
  implicit final class RichDoubleStream(private val stream: DoubleStream) extends AnyVal {
    def accumulate = stream.collect(DoubleAccumulator.supplier, DoubleAccumulator.adder, DoubleAccumulator.merger)
    
    def toScala[Coll[_]](implicit cbf: collection.generic.CanBuildFrom[Nothing, Double, Coll[Double]]): Coll[Double] = {
      if (stream.isParallel) accumulate.to[Coll](cbf)
      else {
        val b = cbf()
        stream.forEachOrdered(new java.util.function.DoubleConsumer{ def accept(d: Double) { b += d } })
        b.result()
      }
    }
  }
  
  implicit final class RichIntStream(private val stream: IntStream) extends AnyVal {
    def accumulate = stream.collect(IntAccumulator.supplier, IntAccumulator.adder, IntAccumulator.merger)

    def toScala[Coll[_]](implicit cbf: collection.generic.CanBuildFrom[Nothing, Int, Coll[Int]]): Coll[Int] = {
      if (stream.isParallel) accumulate.to[Coll](cbf)
      else {
        val b = cbf()
        stream.forEachOrdered(new java.util.function.IntConsumer{ def accept(d: Int) { b += d } })
        b.result()
      }
    }
  }
  
  implicit final class RichLongStream(private val stream: LongStream) extends AnyVal {
    def accumulate = stream.collect(LongAccumulator.supplier, LongAccumulator.adder, LongAccumulator.merger)

    def toScala[Coll[_]](implicit cbf: collection.generic.CanBuildFrom[Nothing, Long, Coll[Long]]): Coll[Long] = {
      if (stream.isParallel) accumulate.to[Coll](cbf)
      else {
        val b = cbf()
        stream.forEachOrdered(new java.util.function.LongConsumer{ def accept(d: Long) { b += d } })
        b.result()
      }
    }
  }

  implicit val accumulateDoubleStepper = new AccumulatesFromStepper[Double, DoubleAccumulator] {
    def apply(stepper: Stepper[Double]) = {
      val a = new DoubleAccumulator
      while (stepper.hasStep) a += stepper.nextStep
      a
    }
  }

  implicit val accumulateIntStepper = new AccumulatesFromStepper[Int, IntAccumulator] {
    def apply(stepper: Stepper[Int]) = {
      val a = new IntAccumulator
      while (stepper.hasStep) a += stepper.nextStep
      a
    }
  }

  implicit val accumulateLongStepper = new AccumulatesFromStepper[Long, LongAccumulator] {
    def apply(stepper: Stepper[Long]) = {
      val a = new LongAccumulator
      while (stepper.hasStep) a += stepper.nextStep
      a
    }
  }
}

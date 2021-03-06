package org.aws4s.s3

import cats.effect.Effect
import org.aws4s._
import org.http4s.{Method, Request, Uri}
import fs2.Stream
import cats.implicits._

private [aws4s] case class PutObject[F[_]: Effect](region: Region, bucket: Bucket, name: Uri.Path, obj: Stream[F, Byte], payloadSigning: PayloadSigning) extends Command[F, Unit] {

  override def request: F[Request[F]] =
    ObjectRequests.request[F](Method.PUT, bucket, name, obj).pure[F]

  override def service: Service = Service.s3
}

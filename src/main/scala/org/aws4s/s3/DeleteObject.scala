package org.aws4s.s3

import cats.effect.Effect
import org.aws4s._
import org.http4s.{Method, Request, Uri}
import cats.implicits._

private [aws4s] case class DeleteObject[F[_]: Effect](region: Region, bucket: Bucket, name: Uri.Path) extends Command[F, Unit] {

  override def request: F[Request[F]] =
    ObjectRequests.request[F](Method.DELETE, bucket, name).pure[F]

  override def payloadSigning: PayloadSigning = PayloadSigning.Signed

  override def service: Service = Service.s3
}

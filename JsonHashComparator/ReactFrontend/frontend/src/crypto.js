import crypto from "crypto";

export function generateHmac(payload, key) {
  const hmac = crypto.createHmac("sha256", key);
  hmac.update(JSON.stringify(payload));
  return hmac.digest("base64");
}
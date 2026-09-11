/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.lance.namespace.errors;

import javax.annotation.Nullable;

import java.util.Map;

/**
 * Factory for creating LanceNamespaceException instances from error codes.
 *
 * <p>This class is useful for converting ErrorResponse objects received from the REST API into the
 * appropriate exception types.
 */
public final class ErrorFactory {
  private ErrorFactory() {}

  /**
   * Creates an appropriate exception instance based on the error code.
   *
   * @param code the numeric error code
   * @param message the error message
   * @return the appropriate exception type for the error code
   */
  public static LanceNamespaceException fromErrorCode(int code, String message) {
    return fromErrorCode(code, message, null, null, null);
  }

  /**
   * Creates an appropriate exception instance based on the error code.
   *
   * @param code the numeric error code
   * @param message the error message
   * @param detail optional detailed explanation
   * @param instance optional identifier for this specific error occurrence
   * @return the appropriate exception type for the error code
   */
  public static LanceNamespaceException fromErrorCode(
      int code, String message, @Nullable String detail, @Nullable String instance) {
    return fromErrorCode(code, message, detail, instance, null);
  }

  /**
   * Creates an appropriate exception instance based on the error code.
   *
   * @param code the numeric error code
   * @param message the error message
   * @param detail optional detailed explanation
   * @param instance optional identifier for this specific error occurrence
   * @param context optional context entries associated with the error. For REST namespaces, keys
   *     starting with {@code header.} correspond to HTTP response headers (e.g. {@code
   *     header.retry-after=2}).
   * @return the appropriate exception type for the error code
   */
  public static LanceNamespaceException fromErrorCode(
      int code,
      String message,
      @Nullable String detail,
      @Nullable String instance,
      @Nullable Map<String, String> context) {
    ErrorCode errorCode = ErrorCode.fromCode(code);
    switch (errorCode) {
      case UNSUPPORTED:
        return new UnsupportedOperationException(message, detail, instance, context);
      case NAMESPACE_NOT_FOUND:
        return new NamespaceNotFoundException(message, detail, instance, context);
      case NAMESPACE_ALREADY_EXISTS:
        return new NamespaceAlreadyExistsException(message, detail, instance, context);
      case NAMESPACE_NOT_EMPTY:
        return new NamespaceNotEmptyException(message, detail, instance, context);
      case TABLE_NOT_FOUND:
        return new TableNotFoundException(message, detail, instance, context);
      case TABLE_ALREADY_EXISTS:
        return new TableAlreadyExistsException(message, detail, instance, context);
      case TABLE_INDEX_NOT_FOUND:
        return new TableIndexNotFoundException(message, detail, instance, context);
      case TABLE_INDEX_ALREADY_EXISTS:
        return new TableIndexAlreadyExistsException(message, detail, instance, context);
      case TABLE_TAG_NOT_FOUND:
        return new TableTagNotFoundException(message, detail, instance, context);
      case TABLE_TAG_ALREADY_EXISTS:
        return new TableTagAlreadyExistsException(message, detail, instance, context);
      case TRANSACTION_NOT_FOUND:
        return new TransactionNotFoundException(message, detail, instance, context);
      case TABLE_VERSION_NOT_FOUND:
        return new TableVersionNotFoundException(message, detail, instance, context);
      case TABLE_COLUMN_NOT_FOUND:
        return new TableColumnNotFoundException(message, detail, instance, context);
      case INVALID_INPUT:
        return new InvalidInputException(message, detail, instance, context);
      case CONCURRENT_MODIFICATION:
        return new ConcurrentModificationException(message, detail, instance, context);
      case PERMISSION_DENIED:
        return new PermissionDeniedException(message, detail, instance, context);
      case UNAUTHENTICATED:
        return new UnauthenticatedException(message, detail, instance, context);
      case SERVICE_UNAVAILABLE:
        return new ServiceUnavailableException(message, detail, instance, context);
      case INTERNAL:
        return new InternalException(message, detail, instance, context);
      case INVALID_TABLE_STATE:
        return new InvalidTableStateException(message, detail, instance, context);
      case TABLE_SCHEMA_VALIDATION_ERROR:
        return new TableSchemaValidationException(message, detail, instance, context);
      case THROTTLING:
        return new ThrottlingException(message, detail, instance, context);
      case TABLE_BRANCH_NOT_FOUND:
        return new TableBranchNotFoundException(message, detail, instance, context);
      case TABLE_BRANCH_ALREADY_EXISTS:
        return new TableBranchAlreadyExistsException(message, detail, instance, context);
      default:
        return new InternalException(message, detail, instance, context);
    }
  }
}

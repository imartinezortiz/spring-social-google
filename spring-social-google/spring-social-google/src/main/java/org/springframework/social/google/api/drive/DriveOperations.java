/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.google.api.drive;

import org.springframework.core.io.Resource;

/**
 * Defines operations for integrating with Google Drive.
 * Requires OAuth scope(s) from the following:
 * <ul>
 * <li>https://www.googleapis.com/auth/drive.file</li>
 * <li>https://www.googleapis.com/auth/drive</li>
 * <li>https://www.googleapis.com/auth/drive.apps.readonly</li>
 * <li>https://www.googleapis.com/auth/drive.readonly</li>
 * <li>https://www.googleapis.com/auth/drive.readonly.metadata</li>
 * See <a href="https://developers.google.com/drive/scopes">https://developers.google.com/drive/scopes</a>
 * for details about the different scopes
 * @author Gabriel Axel
 *
 */
public interface DriveOperations {

	/**
	 * Retrieves a file by its ID
	 * @param id the ID to retrieve by
	 * @return the retrieved {@link DriveFile}
	 */
	DriveFile getFile(String id);
	
	/**
	 * Creates a {@link DriveFileQueryBuilder}
	 * @return a new {@link DriveFileQueryBuilder}
	 */
	DriveFileQueryBuilder driveFileQuery();
	
	/**
	 * Returns files and folders under the root folder
	 * @param pageToken
	 * @return {@link DriveFilesPage}
	 */
	DriveFilesPage getRootFiles(String pageToken);
	
	/**
	 * Returns the files and folders under a specified folder
	 * @param parent folder ID or "root"
	 * @param pageToken
	 * @return {@link DriveFilesPage}
	 */
	DriveFilesPage getFiles(String parent, String pageToken);
	
	/**
	 * Returns trashed files and folders
	 * @param pageToken
	 * @return {@link DriveFilesPage}
	 */
	DriveFilesPage getTrashedFiles(String pageToken);
	
	/**
	 * Moves a file to trash
	 * @param id The ID of the file to trash
	 * @return The updated {@link DriveFile}
	 */
	DriveFile trash(String id);
	
	/**
	 * Restores a file from trash
	 * @param id The ID of the file to untrash
	 * return The updated {@link DriveFile}
	 */
	DriveFile untrash(String id);
	
	/**
	 * Stars a file
	 * @param id The ID of the file to star
	 * @return The updated {@link DriveFile}
	 */
	DriveFile star(String id);
	
	/**
	 * Remove the star from a file
	 * @param id The ID of the file to unstar
	 * @return The updated {@link DriveFile}
	 */
	DriveFile unstar(String id);
	
	/**
	 * Hides a file
	 * @param id The ID of the file to hide
	 * @return The updated {@link DriveFile}
	 */
	DriveFile hide(String id);
	
	/**
	 * Unhides a file
	 * @param id The ID of the file to unhide
	 * @return The updated {@link DriveFile}
	 */
	DriveFile unhide(String id);
	
	/**
	 * Permanently deletes a file
	 * @param id The ID of the file to delete
	 */
	void delete(String id);

	/**
	 * Uploads a file using multipart
	 * @param resource Reference to the file's content
	 * @param metadata The file's metadata
	 * @param parameters Parameters for uploading and processing the file
	 * @return The file resource that was created
	 */
	DriveFile upload(Resource resource, DriveFile metadata, UploadParameters parameters);
	
	/**
	 * Creates an empty file with metadata
	 * @param metadata The file's properties
	 * @return {@link DriveFile} representing the created file
	 */
	DriveFile createFileMetadata(DriveFile metadata);
	
	/**
	 * Creates a folder
	 * @param parentId The parent folder ID or "root"
	 * @param name The name of the folder to create
	 * @return {@link DriveFile} representing the created folder
	 */
	DriveFile createFolder(String parentId, String name);
	
	/**
	 * Returns the permissions of a file
	 * @param fileId The ID of the file
	 * @return {@link UserPermissionsList} for the file
	 */
	UserPermissionsList getPermissions(String fileId);
	
	/**
	 * Adds a permission to a file
	 * @param fileId The file ID
	 * @param permission {@link UserPermission} with the permission settings
	 * @param sendNotificationEmails Whether to send notification e-mails
	 * @return the created {@link UserPermission}
	 */
	UserPermission addPermission(String fileId, UserPermission permission, boolean sendNotificationEmails);

	/**
	 * Updates a permission to a file
	 * @param fileId The file ID
	 * @param permissionId the permission ID
	 * @param permission {@link UserPermission} with new role and additional roles
	 * @return the updated {@link UserPermission}
	 */
	UserPermission updatesPermission(String fileId, String permissionId, UserPermission permission);
	
	/**
	 * Removes a permission from a file
	 * @param fileId The file ID
	 * @param permissionId The permission ID
	 */
	void removePermission(String fileId, String permissionId);
}

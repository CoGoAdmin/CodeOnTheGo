/*
 *  This file is part of AndroidIDE.
 *
 *  AndroidIDE is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  AndroidIDE is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *   along with AndroidIDE.  If not, see <https://www.gnu.org/licenses/>.
 */
package org.appdevforall.codeonthego.utils

import android.content.Context
import com.itsaky.androidide.actions.ActionItem.Location.EDITOR_FILE_TABS
import com.itsaky.androidide.actions.ActionItem.Location.EDITOR_FILE_TREE
import com.itsaky.androidide.actions.ActionItem.Location.EDITOR_TOOLBAR
import com.itsaky.androidide.actions.ActionsRegistry
import org.appdevforall.codeonthego.actions.build.ProjectSyncAction
import org.appdevforall.codeonthego.actions.build.QuickRunWithCancellationAction
import org.appdevforall.codeonthego.actions.build.RunTasksAction
import org.appdevforall.codeonthego.actions.editor.CopyAction
import org.appdevforall.codeonthego.actions.editor.CutAction
import org.appdevforall.codeonthego.actions.editor.ExpandSelectionAction
import org.appdevforall.codeonthego.actions.editor.LongSelectAction
import org.appdevforall.codeonthego.actions.editor.PasteAction
import org.appdevforall.codeonthego.actions.editor.SelectAllAction
import org.appdevforall.codeonthego.actions.etc.DisconnectLogSendersAction
import org.appdevforall.codeonthego.actions.etc.FindActionMenu
import org.appdevforall.codeonthego.actions.etc.LaunchAppAction
import org.appdevforall.codeonthego.actions.etc.PreviewLayoutAction
import org.appdevforall.codeonthego.actions.etc.ReloadColorSchemesAction
import org.appdevforall.codeonthego.actions.file.CloseAllFilesAction
import org.appdevforall.codeonthego.actions.file.CloseFileAction
import org.appdevforall.codeonthego.actions.file.CloseOtherFilesAction
import org.appdevforall.codeonthego.actions.file.FormatCodeAction
import org.appdevforall.codeonthego.actions.file.SaveFileAction
import org.appdevforall.codeonthego.actions.file.ShowTooltipAction
import org.appdevforall.codeonthego.actions.filetree.CopyPathAction
import org.appdevforall.codeonthego.actions.filetree.DeleteAction
import org.appdevforall.codeonthego.actions.filetree.NewFileAction
import org.appdevforall.codeonthego.actions.filetree.NewFolderAction
import org.appdevforall.codeonthego.actions.filetree.OpenWithAction
import org.appdevforall.codeonthego.actions.filetree.RenameAction
import org.appdevforall.codeonthego.actions.github.GitHubCommitAction
import org.appdevforall.codeonthego.actions.github.GitHubFetchAction
import org.appdevforall.codeonthego.actions.github.GitHubPullAction
import org.appdevforall.codeonthego.actions.github.GitHubPushAction
import org.appdevforall.codeonthego.actions.text.RedoAction
import org.appdevforall.codeonthego.actions.text.UndoAction

/**
 * Takes care of registering actions to the actions registry for the editor activity.
 *
 * @author Akash Yadav
 */
class EditorActivityActions {

  companion object {

    @JvmStatic
    fun register(context: Context) {
      clear()
      val registry = ActionsRegistry.getInstance()
      var order = 0

      // Toolbar actions
      registry.registerAction(UndoAction(context, order++))
      registry.registerAction(RedoAction(context, order++))
      registry.registerAction(QuickRunWithCancellationAction(context, order++))
      registry.registerAction(RunTasksAction(context, order++))
      registry.registerAction(SaveFileAction(context, order++))
      registry.registerAction(PreviewLayoutAction(context, order++))
      registry.registerAction(FindActionMenu(context, order++))
      registry.registerAction(ProjectSyncAction(context, order++))
      registry.registerAction(ReloadColorSchemesAction(context, order++))
      registry.registerAction(DisconnectLogSendersAction(context, order++))
      registry.registerAction(LaunchAppAction(context, order++))
      registry.registerAction(GitHubCommitAction(context, order++))
      registry.registerAction(GitHubPushAction(context, order++))
      registry.registerAction(GitHubFetchAction(context, order++))
      registry.registerAction(GitHubPullAction(context, order++))

      // editor text actions
      registry.registerAction(ExpandSelectionAction(context, order++))
      registry.registerAction(SelectAllAction(context, order++))
      registry.registerAction(LongSelectAction(context, order++))
      registry.registerAction(CutAction(context, order++))
      registry.registerAction(CopyAction(context, order++))
      registry.registerAction(PasteAction(context, order++))
      registry.registerAction(FormatCodeAction(context, order++))
      registry.registerAction(ShowTooltipAction(context,order++))

      // file tab actions
      registry.registerAction(CloseFileAction(context, order++))
      registry.registerAction(CloseOtherFilesAction(context, order++))
      registry.registerAction(CloseAllFilesAction(context, order++))

      // file tree actions
      registry.registerAction(CopyPathAction(context, order++))
      registry.registerAction(DeleteAction(context, order++))
      registry.registerAction(NewFileAction(context, order++))
      registry.registerAction(NewFolderAction(context, order++))
      registry.registerAction(OpenWithAction(context, order++))
      registry.registerAction(RenameAction(context, order++))
    }

    @JvmStatic
    fun clear() {
      // EDITOR_TEXT_ACTIONS should not be cleared as the language servers register actions there as
      // well
      val locations = arrayOf(EDITOR_TOOLBAR, EDITOR_FILE_TABS, EDITOR_FILE_TREE)
      val registry = ActionsRegistry.getInstance()
      locations.forEach(registry::clearActions)
    }
  }
}

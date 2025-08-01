package com.dmm.bootcamp.yatter2025.ui.timeline

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.dmm.bootcamp.yatter2025.R
import com.dmm.bootcamp.yatter2025.ui.theme.Yatter2025Theme
import com.dmm.bootcamp.yatter2025.ui.timeline.bindingmodel.ImageBindingModel
import com.dmm.bootcamp.yatter2025.ui.timeline.bindingmodel.YweetBindingModel

@Composable
fun YweetRow(
    yweetBindingModel: YweetBindingModel,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        val context = LocalContext.current

        // プレイスホルダー画像の生成
        val placeholder = ResourcesCompat.getDrawable(
            context.resources,
            R.drawable.avatar_placeholder,
            null,
        )

        AsyncImage(
            modifier = Modifier.size(48.dp),
            model = ImageRequest.Builder(context)
                .data(yweetBindingModel.avatar)
                .placeholder(placeholder)
                .error(placeholder)
                .fallback(placeholder)
                .setHeader("User-Agent", "Mozilla/5.0")
                .build(),
            contentDescription = stringResource(id = R.string.public_timeline_avatar_content_description),
            contentScale = ContentScale.Crop,
        )

        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = buildAnnotatedString {
                    // appendで文字列セット
                    append(yweetBindingModel.displayName)
                    withStyle(
                        style = SpanStyle(
                            // 文字色を薄くするために、Content Alpha.mediumを指定
                            color = MaterialTheme.colors.onBackground.copy(alpha = ContentAlpha.medium),
                        )
                    ) {
                        append(" @${yweetBindingModel.username}")
                    }
                },
                maxLines = 1, // 文字列が複数行にならないように指定
                overflow = TextOverflow.Ellipsis, // はみ出した分を「...」で表現
                fontWeight = FontWeight.Bold, // 文字を太字に
            )

            // 投稿本文
            Text(text = yweetBindingModel.content)

            // 添付画像リストを横スクロールで表示
            LazyRow {
                // itemsの第一引数に並べたいデータセットを渡す
                items(yweetBindingModel.attachmentImageList) { attachmentImage ->
                    // データ1件あたりに表示したいコンポーザブルを呼び出す
                    AsyncImage(
                        model = attachmentImage.url,
                        contentDescription = attachmentImage.description,
                        modifier = Modifier
                            .size(120.dp)
                            .padding(vertical = 4.dp),
                        contentScale = ContentScale.Crop,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }
            }
        }
    }
}

@Preview
@Composable
private fun YweetRowPreview() {
    Yatter2025Theme {
        Surface {
            YweetRow(
                yweetBindingModel = YweetBindingModel(
                    id = "id",
                    displayName = "mito",
                    username = "mitohato14",
                    avatar = "https://avatars.githubusercontent.com/u/19385268?v=4",
                    content = "これはプレビュー用の投稿です。",
                    attachmentImageList = listOf(
                        ImageBindingModel(
                            id = "id",
                            type = "image",
                            url = "https://avatars.githubusercontent.com/u/39693306?v=4",
                            description = "sample image"
                        )
                    )
                )
            )
        }
    }
}

@import hexlet.code.dto.urls.UrlsPage

@param UrlsPage page

@template.layout.page(
page = page,
content = @`
	<div class="container-lg mt-5">
		<h1>Сайты</h1>
		@if(page.getUrls().isEmpty())
			<p>Лист сайтов пуст</p>
		@else
			<table class="table table-bordered table-hover mt-3">
				<thead>
				<tr>
					<th class="col-1">ID</th>
					<th>Имя</th>
					<th class="col-2">Последняя проверка</th>
					<th class="col-1">Код ответа</th>
				</tr>
				</thead>
				<tbody>
				@for(var url : page.getUrls())
					<tr>
						<td>
							${url.getId()}
						</td>
						<td>
							<a href="/urls/${url.getId()}">${url.getName()}</a>
						</td>
						<td>
						</td>
						<td>
						</td>
					</tr>
				@endfor
				</tbody>
			</table>
		@endif
	</div>

`
)
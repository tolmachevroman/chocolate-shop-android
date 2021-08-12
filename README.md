# chocolate-shop-android

Companion Android app for an [imaginary chocolate shop web service](https://github.com/tolmachevroman/chocolate-shop) using Navigation, Hilt, LiveData with coroutines and Apollo GraphQL.

## Architecture overview and notes

* The app follows MVVM architecture. 

* `Single Activity` pattern with `Navigation` component. Main `ChocolateListActivity` hosts Fragments (see [navigation graph](https://github.com/tolmachevroman/chocolate-shop-android/blob/master/app/src/main/res/navigation/chocolate_list_graph.xml)). 
One caveat is to manage the backstack when navigation from one Fragment to another. See [this commit](29aad4ef9cb70487ec81aef0b0aa382f89a4e2dd).

* Each Fragment has its `LiveData` classes performing the requests. `LiveDataScopde` provided by ktx libraries allows for Apollo usage with `Coroutines`.  

* `Repository` pattern used, though quite simplified, without local database or any form of synchronising/omitting unnecessary requests. 
The only data source is the local GraphQL web service. 

* Each Fragment inflates its own `Toolbar`. Although `Navigation` component allows for host-centered `AppBarLayout`, I found it difficult to customize, 
even simple things like changing title appearance. Fragment-based `Toolbar`s allow for maximum customization decoupling.

Also, take a look at the [Medium article]() for more information

## Screenshots

<p float="left">
  <img src="https://user-images.githubusercontent.com/560815/129159585-58d3c98d-95f9-40bf-89c9-12f8b50ecd97.png" width="200" />
  <img src="https://user-images.githubusercontent.com/560815/129159693-59200108-dfff-4677-9d96-dfc90dbfed52.png" width="200" /> 
  <img src="https://user-images.githubusercontent.com/560815/129160797-d42cb4d1-c34e-400c-b617-5d74aa737aa7.png" width="200" />
<img src="https://user-images.githubusercontent.com/560815/129161224-ba5bc1f4-e8c2-4b7f-9933-fa0ab425e3cf.png" width="200" />
</p>

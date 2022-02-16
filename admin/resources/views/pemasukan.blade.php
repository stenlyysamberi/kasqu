@extends('main.index')
@section('menu')
     <!-- start page title -->
 <div class="row">
    <div class="col-12">
        <div class="page-title-box">
            <div class="page-title-right">
                <ol class="breadcrumb m-0">
                    <li class="breadcrumb-item"><a href="javascript: void(0);">{{ $menu1 }}</a></li>
                    <li class="breadcrumb-item active">{{ $menu2 }}</li>
                </ol>
            </div>
            <h4 class="page-title">Dashboard</h4>
        </div>
    </div>
</div>     
<!-- end page title --> 

<div class="row">
    <div class="col-12">
        <div class="card-box">
            <div class="row">
                <div class="col-lg-8">
                    <form method="POST" class="form-inline" action="#">
                        <div class="form-group">
                            <label for="inputPassword2" class="sr-only">Search</label>
                            <input type="search" name="keyword" class="form-control" id="inputPassword2" placeholder="Search...">
                        </div>
                        <div class="form-group mx-sm-3">
                            <label for="status-select" class="mr-2">Sort By</label>
                            <select class="custom-select" id="status-select">
                                <option selected="">Semua</option>
                               
                                
                            </select>
                        </div>
                    </form>
                </div>
                <div class="col-lg-4">
                    <div class="text-lg-right mt-3 mt-lg-0">
                        <button type="button" class="btn btn-success waves-effect waves-light mr-1"><i class="mdi mdi-cog"></i></button>

                        <a data-toggle="modal" data-target="#AddBarcode" href="#" class="btn btn-danger waves-effect waves-light"><i class="mdi mdi-plus-circle mr-1"></i> New Product</a>
                    </div>
                </div><!-- end col-->
            </div> <!-- end row -->
        </div> <!-- end card-box -->
    </div> <!-- end col-->
</div>

{{-- <div class="row">
    @foreach ($produk as $item)
    <div class="col-md-6 col-xl-3">
        <div class="card-box product-box">

            <div class="product-action">
                <a href="javascript: void(0);" class="btn btn-success btn-xs waves-effect waves-light"><i class="mdi mdi-pencil"></i></a>
                <form action="/produk_del/{{ $item->id_produk }}" method="post">
                    @csrf
                    @method('delete')
                    <button onclick="return confirm('apakah Anda akan menghapus data ini?')" href="#" class="btn btn-danger btn-xs waves-effect waves-light"><i class="mdi mdi-close"></i></button>

                </form>
            </div>

            <div class="bg-light">
                <img src="{{ asset('storage/' . $item->img) }}" alt="product-pic" class="img-fluid">
            </div>

            <div class="product-info">
                <div class="row align-items-center">
                    <div class="col">
                        <h5 class="font-16 mt-0 sp-line-1"><a href="ecommerce-product-detail.html" class="text-dark">{{ $item->nama }}</a> </h5>
                        <div class="text-warning mb-2 font-13">
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                        </div>
                        <h5 class="m-0"> <span class="text-muted"> Stocks : {{ $item->qty }}</span></h5>
                    </div>
                    <div class="col-auto">
                        <div class="product-price-tag">
                            $49
                        </div>
                    </div>
                </div> <!-- end row -->
            </div> <!-- end product info-->
        </div> <!-- end card-box-->
    </div> <!-- end col-->
    @endforeach
</div> --}}


{{-- Section Modal add --}}
{{-- <form method="POST" action="/create_produk" enctype="multipart/form-data">
    @csrf
    <div id="AddBarcode" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">



        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">

                    <h4 class="modal-title">Add</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                </div>
                <div class="modal-body p-4">

                    <ul class="nav nav-tabs">
                        <li class="nav-item">
                            <a href="#home" data-toggle="tab" aria-expanded="false" class="nav-link">
                                Baru
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="#profile" data-toggle="tab" aria-expanded="true" class="nav-link active">
                               Petunjuk
                           </a>
                       </li>

                   </ul>


                   <div class="tab-content">
                    <div class="tab-pane" id="home">

                     <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="field-3" class="control-label">Barcode</label>
                                <input onchange="tampil();" type="text" name="barcode" required="required" class="form-control" id="barcode" placeholder="Enter Text">

                               
                            </div>
                        </div>

                    </div>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="field-3" class="control-label">Products Name</label>
                                <input type="text" maxlength="30" name="nama" required="required" class="form-control" id="field-3" placeholder="Enter Text">
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="field-3" class="control-label">Satuan Produk</label>
                                <select name="id_satuan" class="form-control">
                                 @foreach ($jenis as $item)
                                     <option value="{{ $item->id_jenis }}">
                                        {{ $item->nama }}
                                     </option>
                                 @endforeach
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="field-3" class="control-label">quantity</label>
                                <input type="number"  maxlength="30" name="qty"  class="form-control" id="field-3" placeholder="Enter Text">
                            </div>
                        </div>
                    </div>



                    <div class="row">
                        <div class="col-md-12">

                         <h4 class="header-title">Image</h4>
                         <p class="sub-header">
                           Set your Image.
                       </p>

                       <input type="file" required="required" name="img" class="form-control" width="100" />

                   </div>
               </div>

           </div>

           <div class="tab-pane show active" id="profile">
        <p>1. Pastikan perangkat mobile sudah terhubung dalam satu network.</p>
        <p>2. Pada saat pengambilan foto produk, pastika resize rasio diubah menjadi 1:1px.</p>
        <p>3. Sebelum simpan pastikan semua form input sudah terisi.</p>
        <p>4. Klik Simpan.</p>
        </div>
    </div>


    <script type="text/javascript">
        function tampil(){
            var cek = document.getElementsById('barcode').value;
            document.getElementsById('result').value = cek;

        }
    </script>






</div> --}}
<div class="modal-footer">
    <button type="button" class="btn btn-secondary waves-effect" data-dismiss="modal">Close</button>
    <button type="submit" class="btn btn-info waves-effect waves-light">Ok, Simpan</button>
</div>

</div>
</div>

</div>

</form>
@endsection
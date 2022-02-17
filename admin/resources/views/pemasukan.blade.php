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

                        <a data-toggle="modal" data-target="#AddMoney" href="#" class="btn btn-danger waves-effect waves-light"><i class="mdi mdi-plus-circle mr-1"></i>Bayar Kas</a>
                    </div>
                </div><!-- end col-->
            </div> <!-- end row -->
        </div> <!-- end card-box -->
    </div> <!-- end col-->
</div>

<div class="row">
    <div class="col-12">
       <div class="card-box">
        <table class="table table-hover">
            <thead>
                <tr>
                  <th scope="col">No</th>
                  <th scope="col">Nama Usaha</th>
                  <th scope="col">Waktu Pembayaraan</th>
                  <th scope="col">Nama Owner/Kabag</th>
                  <th scope="col">Jumlah Pemasukan</th>
                  <th scope="col">Options</th>
                </tr>
              </thead>

              <tbody>
                @foreach ($kasmasuk as $item)
                <tr>
                    <th scope="row"> {{ $loop->iteration }}</th>
                    <td>{{ $item->mitra_id }}</td>
                    <td>{{ $item->tanggal_masuk }}</td>
                    <td>{{ $item->user_id }}</td>
                    <td>{{ $item->jumlah_pemasukan }}</td>
               
                    <td>
                        <div class="row">
                            <div class="btn-group">
                                <a data-toggle="modal" data-target="#edit_userman{{ $item->user_id }}" href="#" class="btn btn-secondary btn-sm"> <i class="fas fa-edit"></i></a>
                               
                               
                                <form action="/del_userman" method="post">
                                    @csrf
                                    <input hidden type="text" name="id" value="{{ $item->user_id }}">
                                    <button onclick="return confirm('apakah Anda akan menghapus data ini?')" class="btn btn-danger btn-sm"><i class="fas fa-trash" aria-hidden="true"></i></button>
                                </form>
                            </div>
                        </div>
                    </td>


                  
                    
                    </div>
                    
                    </form>

                    {{-- End Blok Edit Userman --}}

                  </tr>
                @endforeach
              </tbody>
             
        </table>
       </div>
    </div>

</div>


{{-- Section Modal add --}}
<form method="POST" action="/tamba_kas" enctype="multipart/form-data">
    @csrf
    <div id="AddMoney" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">

        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">

                    <h4 class="modal-title">Tamba Kas Masuk</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                </div>
                <div class="modal-body p-4">


                   <div class="row">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label for="field-3" class="control-label">Nama Pemilik</label>

                            <select name="nama" id="" class="form-control">
                                <option value="">pilih</option>
                                @foreach ($userman as $item)
                                <option value="{{ $item->user_id }}">{{ $item->nama }}</option>
                                @endforeach
                            </select>
                            
                            {{-- <input type="text" required name="nama"  class="form-control" id="field-3" placeholder="Nama Lengkap"> --}}
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label for="field-3" class="control-label">Jumlah Masuk</label>
                            <input type="text" name="nip" required="required" class="form-control" id="field-3" placeholder="No. Pegawai">
                        </div>
                    </div>
                </div>
               
                
                

            

                
                <div class="row">
                    <div class="col-md-12">
                        <div class="form-group no-margin">
                            <label for="field-7" class="control-label">Keterangan</label>
                            <textarea name="alamat" required="required" class="form-control" id="field-7" placeholder="Enter Text"></textarea>
                        </div>
                        
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12">

                         <h4 class="header-title">Image Profil</h4>
                        <p class="sub-header">
                           Set your Profil Image.
                        </p>

                        <input type="file" name="image" class="form-control" width="100" />
                        
                    </div>
            </div>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-secondary waves-effect" data-dismiss="modal">Close</button>
            <button type="submit" class="btn btn-info waves-effect waves-light">Ok, Simpan</button>
        </div>



    </div>


</form>
@endsection
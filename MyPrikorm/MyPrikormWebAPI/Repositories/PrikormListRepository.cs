using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using MyPrikormWebAPI.Interfaces;
using MyPrikormWebAPI.Model.DB.Context;
using MyPrikormWebAPI.Model.DB.Entities;

namespace MyPrikormWebAPI.Repositories
{
    public class PrikormListRepository : IRepositoryPrikormList
    {
        private ApplicationContext db;

        public PrikormListRepository(ApplicationContext context)
        {
            db = context;
        }

        public async Task<List<PrikormList>> GetAll()
        {
            return await db.PrikormLists.ToListAsync();
        }

        public async Task<PrikormList> Get(int id)
        {
            return await db.PrikormLists.FirstOrDefaultAsync(x => x.Id == id);
        }

        public PrikormList Create(PrikormList prikormList)
        {
            if (prikormList != null)
            {
                db.PrikormLists.Add(prikormList);
                Save();
            }

            return prikormList;
        }

        public PrikormList Update(PrikormList prikormList)
        {
            if (!db.PrikormLists.Any(x => x.Id == prikormList.Id))
            {
                return null;
            }

            db.Update(prikormList);
            Save();
            return prikormList;
        }

        public PrikormList Delete(int id)
        {
            PrikormList prikormList = db.PrikormLists.FirstOrDefault(x => x.Id == id);
            if (prikormList == null)
            {
                return prikormList;
            }

            db.PrikormLists.Remove(prikormList);
            Save();
            return prikormList;
        }

        public void Save()
        {
            db.SaveChanges();
        }
    }
}
